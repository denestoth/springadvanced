package com.epam.spring.advanced.homework.repository;

import com.epam.spring.advanced.homework.domain.Event;
import org.springframework.stereotype.Repository;

import java.util.Collection;

public class EventRepositoryImpl
        extends MapBasedIdentityRepository<Event>
        implements EventRepository {
    public EventRepositoryImpl() {
    }

    public EventRepositoryImpl(Collection<Event> events) {
        super(events);
    }

    public EventRepositoryImpl(ObjectLoader<Collection<Event>> loader) {
        super(loader);
    }
}
