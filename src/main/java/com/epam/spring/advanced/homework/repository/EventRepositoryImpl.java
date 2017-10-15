package com.epam.spring.advanced.homework.repository;

import com.epam.spring.advanced.homework.domain.Event;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@Qualifier("eventRepository")
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
