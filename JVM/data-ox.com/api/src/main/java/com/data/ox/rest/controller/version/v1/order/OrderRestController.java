package com.data.ox.rest.controller.version.v1.order;

import com.data.ox.core.common.Page;
import com.data.ox.core.dto.request.CreateOrderRequestDTO;
import com.data.ox.core.dto.response.OrderResponse;
import com.data.ox.domain.logic.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
@RestController("orderRestControllerV1")
public class OrderRestController {

    private final OrderService service;

    @PostMapping("/create")
    public ResponseEntity<OrderResponse> create(CreateOrderRequestDTO request) {
        return ResponseEntity.ok(this.service.execute(request));
    }

    @GetMapping("/read-all")
    public ResponseEntity<Page<OrderResponse>> readAll(int page) {
        return ResponseEntity.ok(this.service.execute(page, 25));
    }
}
