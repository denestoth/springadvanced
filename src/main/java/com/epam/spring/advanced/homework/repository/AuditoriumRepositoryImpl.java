package com.epam.spring.advanced.homework.repository;

import com.epam.spring.advanced.homework.domain.Auditorium;
import org.springframework.stereotype.Repository;

import javax.annotation.Nonnull;
import java.util.Collection;

public class AuditoriumRepositoryImpl extends MapBasedRepository<Auditorium, String> implements AuditoriumRepository {
    public AuditoriumRepositoryImpl() {
        super(Auditorium::getName, Auditorium::setName);
    }

    public AuditoriumRepositoryImpl(@Nonnull Collection<Auditorium> auditoriums) {
        super(Auditorium::getName, Auditorium::setName, auditoriums);
    }

    public AuditoriumRepositoryImpl(@Nonnull ObjectLoader<Collection<Auditorium>> loader) {
        super(Auditorium::getName, Auditorium::setName, loader);
    }
}
