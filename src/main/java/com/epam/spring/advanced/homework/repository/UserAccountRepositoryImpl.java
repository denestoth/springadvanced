package com.epam.spring.advanced.homework.repository;

import com.epam.spring.advanced.homework.domain.UserAccount;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * @author Denes Toth
 */
@Repository
public class UserAccountRepositoryImpl extends MapBasedIdentityRepository<UserAccount> implements UserAccountRepository {

    public UserAccountRepositoryImpl() {

    }

    public UserAccountRepositoryImpl(Collection<UserAccount> userAccounts) {
        super(userAccounts);
    }
}
