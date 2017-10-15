package com.epam.spring.advanced.homework.service;

import com.epam.spring.advanced.homework.domain.*;
import com.epam.spring.advanced.homework.repository.TicketRepository;
import com.epam.spring.advanced.homework.service.security.AuthenticationFacade;
import com.epam.spring.advanced.homework.service.settings.BookingSettings;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.*;
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
            @Nonnull List<Seat> seats
    ) {
        double sum = 0.0;
        double basePrice = event.getBasePrice();

        Auditorium auditorium = event.getAuditorium();
        for (Seat seat : seats) {
            double price = seat.isVip()
                    ? (100.0 + bookingSettings.getVipExtraChargePercent()) / 100.0 * basePrice
                    : basePrice;

            sum += price;
        }

        return sum;
    }

    @Override
    public void bookTickets(@Nonnull Set<Ticket> tickets) {
        Set<Ticket> incorrectTickets = tickets.stream()
                .filter(ticket -> ticket.getUser() != null)
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
                                    Objects.equals(t.getEvent().getLocalDateTime(), ticket.getEvent().getLocalDateTime()) &&
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
        return ticketRepository.find(t -> event.equals(t.getEvent()) && dateTime.equals(t.getEvent().getLocalDateTime()) && t.getUser() != null);
    }

}
