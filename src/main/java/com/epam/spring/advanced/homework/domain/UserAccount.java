package com.epam.spring.advanced.homework.domain;

import com.epam.spring.advanced.homework.exception.NotEnougnMoneyException;

import javax.persistence.OneToOne;

/**
 * @author Denes Toth
 */

public class UserAccount extends DomainObject {

    private Long prepaidMoneyAmount;

    @OneToOne
    private User user;

    public UserAccount(Long prepaidMoneyAmount) {
        this.prepaidMoneyAmount = prepaidMoneyAmount;
    }

    public Long getPrepaidMoneyAmount() {
        return prepaidMoneyAmount;
    }

    public void setPrepaidMoneyAmount(Long prepaidMoneyAmount) {
        this.prepaidMoneyAmount = prepaidMoneyAmount;
    }

    public Long addMoney(Long amounToAdd) {
        prepaidMoneyAmount += amounToAdd;
        return prepaidMoneyAmount;
    }

    public Long substractMoney(Long amountToSubstract) {
        if (prepaidMoneyAmount < amountToSubstract) {
            throw new NotEnougnMoneyException("You do not have enough money to buy that ticket.");
        }
        prepaidMoneyAmount -= amountToSubstract;
        return prepaidMoneyAmount;
    }
}
