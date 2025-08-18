package com.data.ox.domain.logic.service;

import com.data.ox.core.common.Page;
import com.data.ox.core.data.OrderData;
import com.data.ox.core.dto.request.CreateOrderRequestDTO;
import com.data.ox.core.dto.response.OrderResponse;
import com.data.ox.domain.logic.converter.OrderConverter;
import com.data.ox.domain.logic.dao.OrderDaoI;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.random.RandomGenerator;

import static com.data.ox.core.data.ClientData.createLightClientData;
import static com.data.ox.domain.logic.utils.JobUtils.simulateWork;

@RequiredArgsConstructor
public class OrderService {

    private final OrderDaoI storage;
    private final OrderConverter converter;
    private final RandomGenerator randomGenerator;

    public OrderResponse execute(CreateOrderRequestDTO request) {
        var startProcessingDataTime = LocalDateTime.now();
        simulateWork(this.randomGenerator);
        return this.converter.convert(this.storage.save(OrderData.builder()
                .supplier(createLightClientData(request.getSupplierId()))
                .consumer(createLightClientData(request.getConsumerId()))
                .startProcessingDateTime(startProcessingDataTime)
                .endProcessingDateTime(LocalDateTime.now())
                .amount(request.getAmount())
                .name(request.getName())
                .build()));
    }

    public Page<OrderResponse> execute(int pageNumber, int limit) {
        var page = this.storage.findAll(pageNumber, limit);
        return new Page<>(pageNumber, page.getTotalPages(), this.converter.convert(page.getCollection()));
    }
}
