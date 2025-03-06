package io.hacken.task.service.transaction.datahandler;

import io.hacken.task.database.dao.transaction.AcceptedTransactionDao;
import io.hacken.task.database.model.AcceptedTransaction;
import io.hacken.task.service.transaction.trotting.TransactionTrottingHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static java.util.concurrent.CompletableFuture.runAsync;

@Slf4j
@RequiredArgsConstructor
public class DefaultTransactionDataHandlerImpl implements TransactionDataHandlerI {

    private final CommonTransactionBuffer transactionBuffer;
    private final TransactionTrottingHandler trottingHandler;
    private final AcceptedTransactionDao acceptedTransactionDao;

    @Override
    public void handleAcceptedTransaction(AcceptedTransaction transaction) {
        if (this.trottingHandler.isNotTimeForTrotting()) {
            this.acceptedTransactionDao.save(transaction);

            if (this.transactionBuffer.isNotEmpty()) {
                runAsync(() -> {
                    this.transactionBuffer.free().forEach(this::handleAcceptedTransaction);
                    log.info("All data received!!!");
                });
            }
        } else {
            this.transactionBuffer.collect(transaction);
        }
    }
}
