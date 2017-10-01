package com.epam.spring.advanced.homework.controllers;

import com.epam.spring.advanced.homework.domain.Event;
import com.epam.spring.advanced.homework.service.BookingService;
import com.epam.spring.advanced.homework.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

/**
 * @author Denes Toth
 */
public class GetPurchasedTicketsController {

    @Autowired
    BookingService bookingService;

    @Autowired
    EventService eventService;

    @RequestMapping(value="/api/tickets", method = RequestMethod.GET)
    public String getTickets(@RequestParam("eventId") Long eventId,
                             @RequestParam("dateTime")LocalDateTime dateTime) {
        Event event = eventService.getById(eventId);
        bookingService.getPurchasedTicketsForEvent(event, dateTime);
        return "index";
    }
}
