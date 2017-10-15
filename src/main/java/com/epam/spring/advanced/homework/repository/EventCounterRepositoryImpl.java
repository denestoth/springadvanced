package com.epam.spring.advanced.homework.repository;

import com.epam.spring.advanced.homework.domain.EventCounter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("eventCounterRepository")
public class EventCounterRepositoryImpl
        extends MapBasedRepository<EventCounter, Long>
        implements EventCounterRepository {

    public EventCounterRepositoryImpl() {
        super(EventCounter::getEventId, EventCounter::setEventId);
    }
}
