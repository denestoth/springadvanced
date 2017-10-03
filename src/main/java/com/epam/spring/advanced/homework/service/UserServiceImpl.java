package com.epam.spring.advanced.homework.service;

import com.epam.spring.advanced.homework.domain.User;
import com.epam.spring.advanced.homework.repository.DomainObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@Service
public class UserServiceImpl extends RepositoryBasedDomainObjectService<User> implements UserService {

    @Autowired
    public UserServiceImpl(DomainObjectRepository<User> useRepository) {
        super(useRepository);
    }

    @Nullable
    @Override
    public User getUserByEmail(@Nonnull String email) {
        return repository.findSingleOrDefault(u -> email.equalsIgnoreCase(u.getEmail()));
    }

    @Override
    public void addUser(@Nonnull User user) {
        repository.add(user);
    }
}
