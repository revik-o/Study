package io.hacken.task.rest.v1.controller.transaction;

import io.hacken.task.rest.v1.dto.response.transaction.TransactionTrottingStateRecord;
import io.hacken.task.service.transaction.trotting.TransactionTrottingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static io.hacken.task.rest.RestApiVersionDeclarations.API_V1_PREFIX;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(API_V1_PREFIX + "/transaction/trotting/control")
public class TransactionTrottingRestController {

    private final TransactionTrottingHandler trottingHandler;

    @Autowired
    public TransactionTrottingRestController(TransactionTrottingHandler trottingHandler) {
        this.trottingHandler = trottingHandler;
    }

    @PutMapping("/start")
    public ResponseEntity<String> start(@RequestParam long seconds) {
        return this.trottingHandler.startTrotting(seconds)
                ? ok().body(OK.getReasonPhrase()) : badRequest().body("Already started");
    }


    @GetMapping("/read/state")
    public ResponseEntity<TransactionTrottingStateRecord> readTrottingState() {
        return ok(new TransactionTrottingStateRecord(!this.trottingHandler.isNotTimeForTrotting()));
    }
}
