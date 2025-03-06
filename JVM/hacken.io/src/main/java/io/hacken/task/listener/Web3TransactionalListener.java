package io.hacken.task.listener;

import io.hacken.task.database.model.AcceptedTransaction;
import io.hacken.task.service.transaction.datahandler.TransactionDataHandlerI;
import io.reactivex.disposables.Disposable;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;
import java.time.LocalDateTime;

import static java.lang.String.format;

@Slf4j
@Service
public class Web3TransactionalListener {

    private final Web3j web3;
    private final TransactionDataHandlerI dataHandler;

    private Disposable transactionListenerContext = null;

    @Autowired
    public Web3TransactionalListener(
            @Value("${hacken.application.credentials.grove-city-chain-prefix}") String groveCityChainPrefix,
            @Value("${hacken.application.credentials.grove-city-application-id}") String apiId,
            TransactionDataHandlerI dataHandler
    ) throws IOException {
        String url = format("https://%s.rpc.grove.city/v1/%s", groveCityChainPrefix, apiId);
        this.web3 = Web3j.build(new HttpService(url));
        this.dataHandler = dataHandler;
        this.checkConnection();
        log.info("Web3 using the " + url);
    }

    private void checkConnection() throws IOException {
        this.web3.web3ClientVersion().send();
        log.info("Web3 connected!!!");
    }

    private void handleAcceptedTransaction(Transaction transaction) {
        var entity = AcceptedTransaction.builder()
                .transactionHash(transaction.getHash())
                .blockNumber(transaction.getBlockNumberRaw())
                .sentFrom(transaction.getFrom())
                .sentTo(transaction.getTo())
                .gas(transaction.getGasRaw())
                .gasPrice(transaction.getGasPriceRaw())
                .date(LocalDateTime.now())
                .build();

        this.dataHandler.handleAcceptedTransaction(entity);
    }

    private void forceStopTransactionListener() {
        if (this.transactionListenerContext != null) {
            while (this.transactionListenerContext.isDisposed()) ;
            log.info("forceStopTransactionListener: Listener stopped");
        }
    }

    public boolean isListenerStillListening() {
        return this.transactionListenerContext != null && !this.transactionListenerContext.isDisposed();
    }

    public boolean stopTransactionListener() {
        if (this.isListenerStillListening()) {
            this.transactionListenerContext.dispose();
            log.info("deployTransactionListener: Listener stopped");
            return true;
        }

        log.info("deployTransactionListener: Already stopped");
        return false;
    }

    public synchronized boolean deployTransactionListener() {
        if (!this.isListenerStillListening()) {
            this.transactionListenerContext = this.web3.pendingTransactionFlowable()
                    .subscribe(this::handleAcceptedTransaction, error -> {
                        log.error("Transaction issues: ", error);
                        this.forceStopTransactionListener();
                        this.deployTransactionListener();
                    });

            log.info("deployTransactionListener: Listener started");
            return true;
        }

        log.info("deployTransactionListener: Already started");
        return false;
    }

    @PreDestroy
    void preDestroy() {
        this.forceStopTransactionListener();
    }
}
