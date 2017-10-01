package com.epam.spring.advanced.homework.repository;

import com.epam.spring.advanced.homework.service.settings.AbstractDiscountStrategy;
import org.springframework.stereotype.Repository;

@Repository
public class DiscountStrategyRepositoryImpl
        extends MapBasedIdentityRepository<AbstractDiscountStrategy>
        implements DiscountStrategyRepository {
}
