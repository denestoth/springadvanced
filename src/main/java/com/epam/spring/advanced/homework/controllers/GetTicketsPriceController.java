package com.epam.spring.advanced.homework.controllers;

import com.epam.spring.advanced.homework.domain.Event;
import com.epam.spring.advanced.homework.domain.User;
import com.epam.spring.advanced.homework.service.BookingService;
import com.epam.spring.advanced.homework.service.EventService;
import com.epam.spring.advanced.homework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * @author Denes Toth
 */
@Controller
public class GetTicketsPriceController {

    @Autowired
    BookingService bookingService;

    @Autowired
    UserService userService;

    @Autowired
    EventService eventService;

    @RequestMapping(value="/api/tickets", method = RequestMethod.GET)
    public String getTickets(@RequestParam("eventId") Long eventId,
                             @RequestParam("dateTime") LocalDateTime dateTime,
                             @RequestParam("userId") Long userId,
                             @RequestParam("seats") List<Long> seats,
                             Model model) {
        Event event = eventService.getById(eventId);
        User user = userService.getById(userId);
        LinkedHashSet<Long> seatsSet = new LinkedHashSet<>(seats);

        bookingService.getTicketsPrice(event, dateTime, user, seatsSet);

        return "index";
    }
}
