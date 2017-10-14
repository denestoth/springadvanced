package com.epam.spring.advanced.homework.service;

import com.epam.spring.advanced.homework.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;

public interface UserService extends AbstractDomainObjectService<User> {

    /**
     * Finding user by email
     * 
     * @param email
     *            Email of the user
     * @return found user or <code>null</code>
     */
    public @Nullable
    User getUserByEmail(@Nonnull String email);

    void addUser(@Nonnull User user);

    public Collection<User> getAll();

    public void update(User user);
}
