package com.epam.spring.advanced.homework.controllers;

import com.epam.spring.advanced.homework.domain.Ticket;
import com.epam.spring.advanced.homework.service.BookingService;
import com.epam.spring.advanced.homework.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Denes Toth
 */
@Controller
public class BookTicketsController {

    @Autowired
    BookingService bookingService;

    @Autowired
    TicketService ticketService;

    @RequestMapping(value = "/api/tickets/book", method = RequestMethod.GET)
    public String bookTickets(@RequestParam("ticketId") List<Long> tickets,
                              Model model) {

        Set<Ticket> ticketSet = ticketService.getAll().stream().filter(ticket -> tickets.contains(ticket.getId())).collect(Collectors.toSet());

        bookingService.bookTickets(ticketSet);

        return "bookSuccess";
    }

}
