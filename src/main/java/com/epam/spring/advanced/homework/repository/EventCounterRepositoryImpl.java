package com.epam.spring.advanced.homework.repository;

import com.epam.spring.advanced.homework.domain.EventCounter;
import org.springframework.stereotype.Repository;

@Repository
public class EventCounterRepositoryImpl
        extends MapBasedRepository<EventCounter, Long>
        implements EventCounterRepository{

    public EventCounterRepositoryImpl() {
        super(EventCounter::getEventId, EventCounter::setEventId);
    }
}
