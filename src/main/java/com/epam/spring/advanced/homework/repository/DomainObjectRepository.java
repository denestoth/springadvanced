package com.epam.spring.advanced.homework.repository;

import com.epam.spring.advanced.homework.domain.DomainObject;

public interface DomainObjectRepository<TEntity extends DomainObject> extends Repository<TEntity, Long> {
}
