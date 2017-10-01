package com.epam.spring.advanced.homework.service.settings;

import com.epam.spring.advanced.homework.domain.DomainObject;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractDiscountStrategy
        extends DomainObject
        implements DiscountStrategy {
}
