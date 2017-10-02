package com.epam.spring.advanced.homework.views;

import java.time.LocalDateTime;

/**
 * @author Denes_Toth
 */
public class TicketView {

    private Long id;

    private String user;

    private String event;

    private LocalDateTime localDateTime;

    private Long seat;

    public TicketView(Long id, String user, String event, LocalDateTime localDateTime, Long seat) {
        this.id = id;
        this.user = user;
        this.event = event;
        this.localDateTime = localDateTime;
        this.seat = seat;
    }

    public Long getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public String getEvent() {
        return event;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public Long getSeat() {
        return seat;
    }
}
