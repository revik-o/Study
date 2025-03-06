package io.hacken.task.utils;

import lombok.experimental.UtilityClass;

import java.util.TimerTask;

@UtilityClass
public final class ConcurrencyUtils {

    public static TimerTask buildScheduledTask(Runnable lambda) {
        return new TimerTask() {
            @Override
            public void run() {
                lambda.run();
            }
        };
    }
}
