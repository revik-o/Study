package io.hacken.task.rest.v1.service.transaction;

import io.hacken.task.listener.Web3TransactionalListener;
import static io.hacken.task.utils.ConcurrencyUtils.buildScheduledTask;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Timer;

@Slf4j
@Service
public class TransactionPauseService {

    private Timer pause = null;
    private final Web3TransactionalListener transactionalListener;

    @Autowired
    public TransactionPauseService(Web3TransactionalListener transactionalListener) {
        this.transactionalListener = transactionalListener;
    }

    public boolean isPauseActivated() {
        return this.pause != null;
    }

    public synchronized boolean setPause(long seconds) {
        if (this.pause == null) {
            this.transactionalListener.stopTransactionListener();
            this.pause = new Timer();
            this.pause.schedule(buildScheduledTask(() -> {
                this.transactionalListener.deployTransactionListener();
                this.pause = null;
                log.info("Pause deactivated");
            }), seconds * 1000);
            log.info("Pause activated");
            return true;
        }

        log.info("Pause already activated");
        return false;
    }
}
