package com.epam.spring.advanced.homework.aspects;

import java.util.Random;

public class LuckyWinnerRandomProbabilityChecker implements LuckyWinnerProbabilityChecker {

    // a standard random implementation should be sufficient for the homework
    private final Random random = new Random();

    private final float probability;

    public LuckyWinnerRandomProbabilityChecker(float probability) {
        if (probability < 0 || probability > 1) {
            throw new IllegalArgumentException("probability");
        }
        this.probability = probability;
    }

    @Override
    public boolean isTrue() {
        return probability == 1.0f || random.nextFloat() < probability;
    }
}
