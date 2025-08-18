package com.data.ox.domain.logic.converter;

import com.data.ox.core.data.OrderData;
import com.data.ox.core.dto.response.OrderResponse;

import java.util.Collection;

public class OrderConverter {

    public final OrderResponse convert(OrderData item) {
        return OrderResponse.builder()
                .id(item.getId())
                .name(item.getName())
                .amount(item.getAmount())
                .supplier(item.getSupplier())
                .consumer(item.getConsumer())
                .saveDateTime(item.getSaveDateTime())
                .endProcessingDateTime(item.getEndProcessingDateTime())
                .startProcessingDateTime(item.getStartProcessingDateTime())
                .build();
    }

    public final Collection<OrderResponse> convert(Collection<OrderData> items) {
        return items.stream()
                .map(this::convert)
                .toList();
    }
}
