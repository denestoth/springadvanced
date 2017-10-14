package com.epam.spring.advanced.homework.exception;

/**
 * @author Denes Toth
 */
public class NotEnougnMoneyException extends RuntimeException {
    public NotEnougnMoneyException(String s) {
        super(s);
    }
}
