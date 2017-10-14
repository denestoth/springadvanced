package com.epam.spring.advanced.homework.service.security;

import org.springframework.security.core.Authentication;

/**
 * @author Denes Toth
 */
public interface AuthenticationFacade {
    Authentication getAuthentication();
}
