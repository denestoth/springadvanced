package com.epam.spring.advanced.homework.repository;

import com.epam.spring.advanced.homework.domain.DiscountCounter;
import org.springframework.stereotype.Repository;

@Repository
public class DiscountCounterRepositoryImpl extends MapBasedIdentityRepository<DiscountCounter> {
}
