package com.epam.spring.advanced.homework.service;

import com.epam.spring.advanced.homework.domain.User;
import com.epam.spring.advanced.homework.repository.DomainObjectRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@Service
public class UserServiceImpl extends RepositoryBasedDomainObjectService<User> implements UserService {
    public UserServiceImpl(DomainObjectRepository<User> repository) {
        super(repository);
    }

    @Nullable
    @Override
    public User getUserByEmail(@Nonnull String email) {
        return repository.findSingleOrDefault(u -> email.equalsIgnoreCase(u.getEmail()));
    }
}
