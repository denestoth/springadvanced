package com.epam.spring.advanced.homework.service;

import com.epam.spring.advanced.homework.domain.*;
import com.epam.spring.advanced.homework.repository.TicketRepository;
import com.epam.spring.advanced.homework.service.security.AuthenticationFacade;
import com.epam.spring.advanced.homework.service.settings.BookingSettings;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingSettings bookingSettings;
    private final TicketRepository ticketRepository;
    private final DiscountService discountService;
    private final AuthenticationFacade authenticationFacade;
    private final UserService userService;
    private final Object bookingLocker = new Object();

    public BookingServiceImpl(
            BookingSettings bookingSettings,
            TicketRepository ticketRepository,
            UserService userService,
            DiscountService discountService,
            AuthenticationFacade authenticationFacade
    ) {
        this.bookingSettings = bookingSettings;
        this.ticketRepository = ticketRepository;
        this.discountService = discountService;
        this.authenticationFacade = authenticationFacade;
        this.userService = userService;
    }

    @Override
    public double getTicketsPrice(
            @Nonnull Event event,
            @Nonnull LocalDateTime dateTime,
            @Nullable User user,
            @Nonnull LinkedHashSet<Long> seats
    ) {
        double sum = 0.0;
        double basePrice = event.getBasePrice();
        if (event.getRating() == EventRating.HIGH) {
            basePrice = (100.0 + bookingSettings.getHighRatedExtraChargePercent()) / 100.0 * basePrice;
        }
        Map<Long, DiscountService.ApplicableDiscountInfo> discountMap =
                discountService.getDiscount(user, event, dateTime, seats);

        Auditorium auditorium = event.getAuditoriums().get(dateTime);
        for (long seat : seats) {
            double price = auditorium.isVipSeat(seat)
                    ? (100.0 + bookingSettings.getVipExtraChargePercent()) / 100.0 * basePrice
                    : basePrice;

            DiscountService.ApplicableDiscountInfo discount = discountMap.get(seat);
            if (discount != null) {
                price = (100.0 - discount.getDiscountPercent()) / 100.0 * price;
            }

            sum += price;
        }

        return sum;
    }

    @Override
    public void bookTickets(@Nonnull Set<Ticket> tickets) {
        Set<Ticket> incorrectTickets = tickets.stream()
                // for simplicity let's not do all the possible checks against the tickets
                .filter(ticket -> !ticket.getEvent().getAirDates().contains(ticket.getDateTime()) ||
                        ticket.getSeat() > ticket.getEvent().getAuditoriums().get(ticket.getDateTime()).getNumberOfSeats())
                .collect(Collectors.toSet());

        if (!incorrectTickets.isEmpty()) {
            throw new IllegalArgumentException(String.format("Incorrect tickets: %s", incorrectTickets));
        }

        Long sumPrice = 0L;

        for (Ticket ticket : tickets) {
            sumPrice += ticket.getEvent().getTicketPrice();
        }

        userService.getUserByEmail(authenticationFacade.getAuthentication().getName()).getUserAccount().substractMoney(sumPrice);

        synchronized (bookingLocker) {
            Set<Ticket> alreadyBookedTickets = ticketRepository
                    .find(t -> tickets.stream()
                            .anyMatch(ticket -> Objects.equals(t.getEvent(), ticket.getEvent()) &&
                                    Objects.equals(t.getSeat(), ticket.getSeat()) &&
                                    Objects.equals(t.getDateTime(), ticket.getDateTime()) &&
                                    t.getUser() != null));

            if (!alreadyBookedTickets.isEmpty()) {
                throw new RuntimeException(String.format("Tickets already booked: %s", alreadyBookedTickets));
            }

            for (Ticket ticket : tickets) {
                User user = userService.getAll().stream().collect(Collectors.toList()).get(0);
                if (user != null) {
                    user.getTickets().add(ticket);
                    if (user.getId() != null) {
                        userService.update(user);
                    }
                    ticket.setUser(user);
                }
            }
        }
    }

    @Nonnull
    @Override
    public Set<Ticket> getPurchasedTicketsForEvent(@Nonnull Event event, @Nonnull LocalDateTime dateTime) {
        return ticketRepository.find(t -> event.equals(t.getEvent()) && dateTime.equals(t.getDateTime()) && t.getUser() != null);
    }

}
