package com.epam.spring.advanced.homework.aspects;

import com.epam.spring.advanced.homework.domain.Event;
import com.epam.spring.advanced.homework.domain.User;
import com.epam.spring.advanced.homework.domain.UserLuckyEventInfo;
import com.epam.spring.advanced.homework.service.UserService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class LuckyWinnerAspect {

    private final LuckyWinnerProbabilityChecker probabilityChecker;

    private final UserService userService;

    @Autowired
    public LuckyWinnerAspect(UserService userService) {
        this.probabilityChecker = new LuckyWinnerRandomProbabilityChecker(0.8f);
        this.userService = userService;
    }

    @Around("execution(* com.epam.spring.advanced.homework.service.BookingService.getTicketsPrice(..))")
    public Object handleGetPurchasingPrice(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        User user = (User) args[2];

        if (user == null || !probabilityChecker.isTrue()) {
            return joinPoint.proceed();
        }

        Event event = (Event) args[0];
        LocalDateTime airDate = (LocalDateTime) args[1];

        user.addLuckyEvent(new UserLuckyEventInfo(user.getId(), event.getId(), airDate));
        userService.save(user);

        return 0.0;
    }
}
