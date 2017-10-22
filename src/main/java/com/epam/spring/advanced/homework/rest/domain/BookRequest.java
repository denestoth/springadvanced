package com.epam.spring.advanced.homework.rest.domain;

import com.epam.spring.advanced.homework.views.SimpleTicketView;

import java.util.Set;

/**
 * @author Denes Toth
 */
public class BookRequest {
    private Set<SimpleTicketView> tickets;

    public Set<SimpleTicketView> getTickets() {
        return tickets;
    }

    public void setTickets(Set<SimpleTicketView> tickets) {
        this.tickets = tickets;
    }
}
