package com.epam.spring.advanced.homework.domain;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Objects;

public class Ticket extends DomainObject implements Comparable<Ticket> {

    @ManyToOne
    private User user;

    @OneToOne
    private Event event;

    @OneToOne
    private Seat seat;

    public Ticket(User user, Event event, Seat seat) {
        this.user = user;
        this.event = event;
        this.seat = seat;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public Seat getSeat() {
        return seat;
    }

    @Override
    public int hashCode() {
        return Objects.hash(event, seat);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Ticket other = (Ticket) obj;

        if (event == null) {
            if (other.event != null) {
                return false;
            }
        } else if (!event.equals(other.event)) {
            return false;
        }
        if (seat != other.seat) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Ticket other) {
        if (other == null) {
            return 1;
        }
        int result = result = event.getName().compareTo(other.getEvent().getName());

        if (result == 0) {
            result = (seat.equals(other.getSeat()) ? 1 : 0);
        }
        return result;
    }

}
