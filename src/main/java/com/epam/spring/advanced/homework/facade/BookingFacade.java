package com.epam.spring.advanced.homework.facade;

import com.epam.spring.advanced.homework.domain.User;

/**
 * @author Denes Toth
 */
public class BookingFacade {

    public Long addMoney(User user, Long amount) {
        return user.getUserAccount().addMoney(amount);
    }
}
