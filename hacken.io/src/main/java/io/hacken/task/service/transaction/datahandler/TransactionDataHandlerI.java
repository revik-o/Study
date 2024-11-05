package io.hacken.task.service.transaction.datahandler;

import io.hacken.task.database.model.AcceptedTransaction;

public interface TransactionDataHandlerI {

    void handleAcceptedTransaction(AcceptedTransaction transaction);
}
