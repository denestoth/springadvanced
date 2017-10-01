package com.epam.spring.advanced.homework.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.*;

public class User extends DomainObject {

    private final Set<UserLuckyEventInfo> luckyEvents = new HashSet<>();
    private final Object luckyEventsLocker = new Object();

    private String firstName;

    private String lastName;

    private String email;

    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    private NavigableSet<Ticket> tickets = new TreeSet<>();

    public final Set<UserLuckyEventInfo> getLuckyEvents() {
        synchronized (luckyEventsLocker) {
            return new HashSet<>(luckyEvents);
        }
    }

    public void addLuckyEvent(UserLuckyEventInfo luckyEvent) {
        synchronized (luckyEventsLocker) {
            luckyEvents.add(luckyEvent);
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate getBirthday() {
        return birthday;
    }

    //Why the following DateFormats do not work? had to fall back to SpEL...
    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    public void setBirthday(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate birthday) {
        this.birthday = birthday;
    }

    public NavigableSet<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(NavigableSet<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, birthday);
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
        User other = (User) obj;
        if (email == null) {
            if (other.email != null) {
                return false;
            }
        } else if (!email.equals(other.email)) {
            return false;
        }
        if (firstName == null) {
            if (other.firstName != null) {
                return false;
            }
        } else if (!firstName.equals(other.firstName)) {
            return false;
        }
        if (lastName == null) {
            if (other.lastName != null) {
                return false;
            }
        } else if (!lastName.equals(other.lastName)) {
            return false;
        }
        if (birthday == null) {
            if (other.birthday != null) {
                return false;
            }
        } else if (!birthday.equals(other.birthday)) {
            return false;
        }
        return true;
    }

}
