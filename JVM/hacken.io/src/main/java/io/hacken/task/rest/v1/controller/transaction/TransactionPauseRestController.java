package io.hacken.task.rest.v1.controller.transaction;

import io.hacken.task.rest.v1.dto.response.transaction.TransactionTrottingStateRecord;
import io.hacken.task.rest.v1.service.transaction.TransactionPauseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static io.hacken.task.rest.RestApiVersionDeclarations.API_V1_PREFIX;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(API_V1_PREFIX + "/transaction/pause/control")
public class TransactionPauseRestController {

    private final TransactionPauseService pauseService;

    @Autowired
    public TransactionPauseRestController(TransactionPauseService pauseService) {
        this.pauseService = pauseService;
    }

    @PutMapping("/start")
    public ResponseEntity<String> start(@RequestParam long seconds) {
        return this.pauseService.setPause(seconds)
                ? ok().body(OK.getReasonPhrase()) : badRequest().body("Already started");
    }


    @GetMapping("/read/state")
    public ResponseEntity<TransactionTrottingStateRecord> readTrottingState() {
        return ok(new TransactionTrottingStateRecord(!this.pauseService.isPauseActivated()));
    }
}
