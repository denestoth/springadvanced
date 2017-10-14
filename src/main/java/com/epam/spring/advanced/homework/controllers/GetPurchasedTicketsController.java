package com.epam.spring.advanced.homework.controllers;

import com.epam.spring.advanced.homework.domain.Event;
import com.epam.spring.advanced.homework.domain.Ticket;
import com.epam.spring.advanced.homework.service.BookingService;
import com.epam.spring.advanced.homework.service.EventService;
import com.epam.spring.advanced.homework.transformers.TicketTransformer;
import com.epam.spring.advanced.homework.views.TicketView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Denes Toth
 */
@Controller
public class GetPurchasedTicketsController {

    @Autowired
    BookingService bookingService;

    @Autowired
    EventService eventService;

    @Autowired
    TicketTransformer ticketTransformer;

    @RequestMapping(value = "/api/tickets/purchased", method = RequestMethod.GET)
    public String getPurchasedTicketsForEvent(@RequestParam("eventId") Long eventId,
                                              @RequestParam("dateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime,
                                              Model model) {
        Event event = eventService.getById(eventId);
        List<Ticket> tickets = bookingService.getPurchasedTicketsForEvent(event, dateTime).stream().collect(Collectors.toList());
        List<TicketView> ticketViews = ticketTransformer.dtosToViews(tickets);

        model.addAttribute("tickets", ticketViews);

        return "purchasedTickets";
    }

    @RequestMapping(value = "/api/tickets/purchasedPdf", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public ModelAndView getPurchasedTicketsForEventAsPdf(@RequestParam("eventId") Long eventId,
                                                         @RequestParam("dateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime,
                                                         Model model) {

        Event event = eventService.getById(eventId);
        List<Ticket> tickets = bookingService.getPurchasedTicketsForEvent(event, dateTime).stream().collect(Collectors.toList());
        List<TicketView> ticketViews = ticketTransformer.dtosToViews(tickets);

        return new ModelAndView("pdfPurchasedTicketView", "tickets", ticketViews);
    }
}
