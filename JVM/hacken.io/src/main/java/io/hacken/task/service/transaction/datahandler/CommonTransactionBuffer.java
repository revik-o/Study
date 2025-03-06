package io.hacken.task.service.transaction.datahandler;

import io.hacken.task.database.model.AcceptedTransaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

@Slf4j
@Component
public class CommonTransactionBuffer {

    private final Collection<AcceptedTransaction> bufferedTransactions = new LinkedList<>();

    public void collect(AcceptedTransaction transaction) {
        this.bufferedTransactions.add(transaction);
    }

    public boolean isNotEmpty() {
        return !this.bufferedTransactions.isEmpty();
    }

    public synchronized Collection<AcceptedTransaction> free() {
        var result = new ArrayList<>(this.bufferedTransactions);
        this.bufferedTransactions.clear();
        log.info("Free: TransactionBuffer");
        return result;
    }
}
