package io.hacken.task.service.transaction.trotting;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static java.lang.System.currentTimeMillis;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionTrottingHandler {

    private long endDateOfTrotting = currentTimeMillis();

    public boolean isNotTimeForTrotting() {
        return currentTimeMillis() >= this.endDateOfTrotting;
    }

    public synchronized boolean startTrotting(long durationInSeconds) {
        if (this.isNotTimeForTrotting()) {
            this.endDateOfTrotting = currentTimeMillis() + (durationInSeconds * 1000);
            log.info("Start trotting...");
            return true;
        }

        return false;
    }
}
