package com.epam.spring.advanced.homework.service;

import com.epam.spring.advanced.homework.domain.Auditorium;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;

public interface AuditoriumService {

    /**
     * Getting all auditoriums from the system
     * 
     * @return set of all auditoriums
     */
    public @Nonnull
    Set<Auditorium> getAll();

    /**
     * Finding auditorium by name
     * 
     * @param name
     *            Name of the auditorium
     * @return found auditorium or <code>null</code>
     */
    public @Nullable
    Auditorium getByName(@Nonnull String name);

}
