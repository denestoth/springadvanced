package com.epam.spring.advanced.homework.rest.controllers;

import com.epam.spring.advanced.homework.domain.Event;
import com.epam.spring.advanced.homework.domain.Ticket;
import com.epam.spring.advanced.homework.domain.User;
import com.epam.spring.advanced.homework.rest.domain.BookRequest;
import com.epam.spring.advanced.homework.service.BookingService;
import com.epam.spring.advanced.homework.service.EventService;
import com.epam.spring.advanced.homework.service.UserService;
import com.epam.spring.advanced.homework.views.SimpleTicketView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Denes Toth
 */
@RestController
@RequestMapping(value = "/api/tickets")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    //Example query string: price?eventId=40&dateTime=1995-06-25T20:00Z&userId=50&seats=1
    @RequestMapping(value = "/price", method = RequestMethod.GET)
    public ResponseEntity<Double> getTicketPrice(@RequestParam("eventId") Long eventId, @RequestParam("userId") Long userId,
                                                 @RequestParam("dateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime, @RequestParam("seats") String seats) {



        Double ticketsPrice = bookingService.getTicketsPrice(eventService.getById(eventId), dateTime, userService.getById(userId), transfomrSeatNumbers(seats));
        return new ResponseEntity<>(ticketsPrice, HttpStatus.OK);
    }

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public ResponseEntity bookTickets(@RequestBody BookRequest bookRequest) {
        bookRequest.getTickets().stream().map(simpleTicketView -> simpleTicketViewToTicket(simpleTicketView)).collect(Collectors.toSet());
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = {"application/json", "application/pdf"})
    @ResponseBody
    public Set<Ticket> getTicketsForEvent(@RequestParam("eventId") Long eventId,
                                          @RequestParam("dateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime) {
        return bookingService.getPurchasedTicketsForEvent(eventService.getById(eventId), dateTime);
    }

    private LinkedHashSet<Long> transfomrSeatNumbers(String seats) {
        String[] seatsNumbers = seats.split(",");
        LinkedHashSet<Long> seatSet = new LinkedHashSet<>();

        for (String seat : seatsNumbers) {
            seatSet.add(Long.parseLong(seat));
        }

        return seatSet;
    }

    private Ticket simpleTicketViewToTicket(SimpleTicketView simpleTicketView) {
        User user = new User();
        user.setId(simpleTicketView.getUserId());
        Event event = new Event();
        event.setId(simpleTicketView.getEventId());
        return new Ticket(user, event, simpleTicketView.getDateTime(), simpleTicketView.getSeat());
    }
}
