package com.epam.spring.advanced.homework.transformers;

import com.epam.spring.advanced.homework.domain.Ticket;
import com.epam.spring.advanced.homework.views.TicketView;
import org.springframework.stereotype.Component;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Denes_Toth
 */
@Component
public class TicketTransformer implements Transformer<Ticket, TicketView> {
    @Override
    public Ticket viewToDto(TicketView ticketView) {
        throw new NotImplementedException();
    }

    @Override
    public List<Ticket> viewsToDtos(List<TicketView> ticketViews) {
        throw new NotImplementedException();
    }

    @Override
    public TicketView dtoToView(Ticket ticket) {
        String user = ticket.getUser() != null ? ticket.getUser().getFirstName() + " " + ticket.getUser().getLastName() : "";
        String event = ticket.getEvent() != null ? ticket.getEvent().getName() : "";

        return new TicketView(ticket.getId(), user, event, ticket.getDateTime(), ticket.getSeat());
    }

    @Override
    public List<TicketView> dtosToViews(List<Ticket> tickets) {
        return tickets.stream().map(ticket -> dtoToView(ticket)).collect(Collectors.toList());
    }
}
