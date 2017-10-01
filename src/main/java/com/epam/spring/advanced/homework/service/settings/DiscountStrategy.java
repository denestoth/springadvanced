package com.epam.spring.advanced.homework.service.settings;

import com.epam.spring.advanced.homework.domain.Event;
import com.epam.spring.advanced.homework.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;

public interface DiscountStrategy {
    float getDiscount(
            @Nullable User user,
            @Nonnull Event event,
            @Nonnull LocalDateTime airDateTime,
            int numberOfTickets,
            int ticketNumberInOrder);
}
