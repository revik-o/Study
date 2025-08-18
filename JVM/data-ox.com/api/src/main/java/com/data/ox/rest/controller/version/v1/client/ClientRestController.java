package com.data.ox.rest.controller.version.v1.client;

import com.data.ox.core.dto.request.CreateClientRequestDTO;
import com.data.ox.core.dto.request.UpdateClientRequestDTO;
import com.data.ox.core.dto.response.LightClientResponse;
import com.data.ox.domain.logic.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/client")
@RestController("clientRestControllerV1")
public class ClientRestController {

    private final ClientService service;

    @GetMapping("/search")
    public void search() {
//        TODO Поиск клиентов по ключевому слову (поиск по имени, email,
//          адресу и т.д - частичное совпадение от 3х символов,
//          регистронезависимость)
    }

    @PostMapping("/create")
    public ResponseEntity<LightClientResponse> create(CreateClientRequestDTO request) {
        return ResponseEntity.ok(this.service.execute(request));
    }

    @GetMapping("/read")
    public ResponseEntity<LightClientResponse> read(long request) {
        return ResponseEntity.ok(this.service.execute(request));
    }

    @PutMapping("/update")
    public ResponseEntity<LightClientResponse> update(UpdateClientRequestDTO request) {
        return ResponseEntity.ok(this.service.execute(request));
    }
}
