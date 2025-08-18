package com.data.ox.domain.logic.utils;

import lombok.experimental.UtilityClass;

import java.time.Duration;
import java.util.random.RandomGenerator;

@UtilityClass
public final class JobUtils {

    public static void simulateWork(RandomGenerator random) {
        try {
            Thread.sleep(Duration.ofSeconds(random.nextInt(1, 11)));
        } catch (InterruptedException e) {
            throw new RuntimeException(e); // TODO
        }
    }
}
