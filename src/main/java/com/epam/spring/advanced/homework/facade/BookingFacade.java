package com.epam.spring.advanced.homework.facade;

import com.epam.spring.advanced.homework.domain.User;
import com.epam.spring.advanced.homework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Denes Toth
 */
public class BookingFacade {

    public Long addMoney(User user, Long amount) {
        return user.getUserAccount().addMoney(amount);
    }
}
