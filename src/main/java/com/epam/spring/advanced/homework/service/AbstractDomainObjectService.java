package com.epam.spring.advanced.homework.service;

import com.epam.spring.advanced.homework.domain.DomainObject;

import java.util.Collection;
import javax.annotation.Nonnull;

public interface AbstractDomainObjectService<T extends DomainObject> {

    /**
     * Saving new object to storage or updating existing one.
     * 
     * @param object
     *            Object to save
     * @return saved object with assigned id
     */
    T save(@Nonnull T object);

    /**
     * Removing object from storage
     * 
     * @param object
     *            Object to remove
     */
    void remove(@Nonnull T object);

    /**
     * Getting object by id from storage.
     * 
     * @param id
     *            id of the object
     * @return Found object or <code>null</code>
     */
    T getById(@Nonnull Long id);

    /**
     * Getting all objects from storage.
     * 
     * @return collection of objects
     */
    public @Nonnull
    Collection<T> getAll();
}
