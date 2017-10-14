package com.epam.spring.advanced.homework.service;

import com.epam.spring.advanced.homework.domain.UserAccount;
import com.epam.spring.advanced.homework.repository.DomainObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Denes Toth
 */
@Service
public class UserAccountServiceImpl extends RepositoryBasedDomainObjectService<UserAccount> {

    @Autowired
    public UserAccountServiceImpl(DomainObjectRepository<UserAccount> userAccountRepository) {
        super(userAccountRepository);
    }

}
