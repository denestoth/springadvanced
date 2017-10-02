package com.epam.spring.advanced.homework.service;

import com.epam.spring.advanced.homework.domain.Event;
import com.epam.spring.advanced.homework.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

@Service
public class EventServiceImpl extends RepositoryBasedDomainObjectService<Event> implements EventService {

    private Clock clock = Clock.systemUTC();

    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        super(eventRepository);
    }

    @Nullable
    @Override
    public Event getByName(@Nonnull String name) {
        return repository.findSingleOrDefault(e -> name.equals(e.getName()));
    }

    @Nonnull
    @Override
    public Set<Event> getForDateRange(@Nonnull LocalDate from, @Nonnull LocalDate to) {
        LocalDateTime lowerBound = LocalDateTime.of(from, LocalTime.MIN);
        LocalDateTime upperBound = LocalDateTime.of(to, LocalTime.MAX);
        return repository.find(event -> !event.getAirDates()
                .subSet(lowerBound, true, upperBound, true)
                .isEmpty());
    }

    @Nonnull
    @Override
    public Set<Event> getNextEvents(@Nonnull LocalDateTime to) {
        LocalDateTime lowerBound = LocalDateTime.now(clock);
        if (to.isBefore(lowerBound)) {
            throw new IllegalArgumentException("to");
        }
        return repository.find(event -> !event.getAirDates()
                .subSet(lowerBound, true, to, true)
                .isEmpty());
    }

    public Clock getClock() {
        return clock;
    }
}
