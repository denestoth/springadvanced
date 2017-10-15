package com.epam.spring.advanced.homework.domain;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Objects;

/**
 * @author Denes Toth
 */
public class Seat extends DomainObject implements Comparable<Seat> {

    @Column
    private Long number;

    @ManyToOne
    private Auditorium auditorium;

    @OneToMany
    private List<Ticket> tickets;

    @Column
    private boolean isVip;

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Auditorium getAuditorium() {
        return auditorium;
    }

    public void setAuditorium(Auditorium auditorium) {
        this.auditorium = auditorium;
    }

    public boolean isVip() {
        return isVip;
    }

    public void setVip(boolean vip) {
        isVip = vip;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, auditorium);
    }

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

        Seat other = (Seat) obj;

        if (!this.auditorium.equals(other.getAuditorium()) || !this.number.equals(other.number)) {
            return false;
        }

        return true;
    }

    @Override
    public int compareTo(Seat o) {
        return 0;
    }
}
