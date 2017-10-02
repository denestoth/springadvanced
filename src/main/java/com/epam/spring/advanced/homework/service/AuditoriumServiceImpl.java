package com.epam.spring.advanced.homework.service;

import com.epam.spring.advanced.homework.domain.Auditorium;
import com.epam.spring.advanced.homework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Set;

@Service
public class AuditoriumServiceImpl implements AuditoriumService {

    private final Repository<Auditorium, String> repository;

    @Autowired
    public AuditoriumServiceImpl(Repository<Auditorium, String> auditoriumRepository) {
        this.repository = auditoriumRepository;
    }

    @Nonnull
    @Override
    public Set<Auditorium> getAll() {
        return new HashSet<>(repository.getAll());
    }

    @Nullable
    @Override
    public Auditorium getByName(@Nonnull String name) {
        return repository.findById(name);
    }
}
