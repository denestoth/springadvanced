package com.epam.spring.advanced.homework.repository;

import com.epam.spring.advanced.homework.domain.DiscountCounter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("discountCounterRepository")
public class DiscountCounterRepositoryImpl extends MapBasedIdentityRepository<DiscountCounter> {
}
