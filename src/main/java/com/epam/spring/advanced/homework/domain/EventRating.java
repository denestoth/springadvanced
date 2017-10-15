package com.epam.spring.advanced.homework.domain;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.List;

//    LOW, MID, HIGH
public class EventRating extends DomainObject {

    @Column
    private String name;

    @OneToMany
    private List<Event> events;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
