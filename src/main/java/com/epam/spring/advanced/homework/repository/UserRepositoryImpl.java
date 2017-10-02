package com.epam.spring.advanced.homework.repository;

import com.epam.spring.advanced.homework.domain.User;

import java.util.Collection;

public class UserRepositoryImpl
        extends MapBasedIdentityRepository<User>
        implements UserRepository {
    public UserRepositoryImpl() {
    }

    public UserRepositoryImpl(Collection<User> users) {
        super(users);
    }
}
