package io.hacken.task.rest.v1.controller.transaction;

import io.hacken.task.listener.Web3TransactionalListener;
import io.hacken.task.rest.v1.dto.response.transaction.TransactionListenerStateRecord;
import io.hacken.task.rest.v1.dto.response.transaction.TransactionTrottingStateRecord;
import io.hacken.task.service.transaction.trotting.TransactionTrottingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static io.hacken.task.rest.RestApiVersionDeclarations.API_V1_PREFIX;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(API_V1_PREFIX + "/transaction/listener/control")
public class TransactionListenerRestController {

    private final TransactionTrottingHandler trottingHandler;
    private final Web3TransactionalListener transactionalListenerService;

    @Autowired
    public TransactionListenerRestController(TransactionTrottingHandler trottingHandler,
                                             Web3TransactionalListener transactionalListenerService) {
        this.trottingHandler = trottingHandler;
        this.transactionalListenerService = transactionalListenerService;
    }

    @PutMapping("/start")
    public ResponseEntity<String> start() {
        return this.transactionalListenerService.deployTransactionListener()
                ? ok().body(OK.getReasonPhrase()) : badRequest().body("Already started");
    }

    @PutMapping("/stop")
    public ResponseEntity<String> stop() {
        return this.transactionalListenerService.stopTransactionListener()
                ? ok().body(OK.getReasonPhrase()) : badRequest().body("Already stopped");
    }

    @GetMapping("/read/state")
    public ResponseEntity<TransactionListenerStateRecord> readState() {
        return ok(new TransactionListenerStateRecord(this.transactionalListenerService.isListenerStillListening()));
    }
}
