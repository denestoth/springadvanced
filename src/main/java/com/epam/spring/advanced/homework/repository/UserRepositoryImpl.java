package com.epam.spring.advanced.homework.repository;

import com.epam.spring.advanced.homework.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class UserRepositoryImpl
        extends MapBasedIdentityRepository<User>
        implements UserRepository {
    public UserRepositoryImpl() {
    }

    public UserRepositoryImpl(Collection<User> users) {
        super(users);
    }
}
