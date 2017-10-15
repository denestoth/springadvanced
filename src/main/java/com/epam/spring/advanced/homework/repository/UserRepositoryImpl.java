package com.epam.spring.advanced.homework.repository;

import com.epam.spring.advanced.homework.domain.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@Qualifier("userRepository")
public class UserRepositoryImpl
        extends MapBasedIdentityRepository<User>
        implements UserRepository {
    public UserRepositoryImpl() {
    }

    public UserRepositoryImpl(Collection<User> users) {
        super(users);
    }
}
