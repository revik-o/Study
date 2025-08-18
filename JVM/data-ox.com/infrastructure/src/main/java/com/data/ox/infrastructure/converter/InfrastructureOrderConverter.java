package com.data.ox.infrastructure.converter;

import com.data.ox.core.data.OrderData;
import com.data.ox.infrastructure.model.OrderModel;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class InfrastructureOrderConverter {

    public OrderData convert(InfrastructureClientConverter clientConverter, OrderModel item) {
        return OrderData.builder()
                .id(item.getId())
                .name(item.getName())
                .supplier(clientConverter.convert(this, item.getSupplier()))
                .consumer(clientConverter.convert(this, item.getConsumer()))
                .amount(item.getAmount())
                .saveDateTime(item.getSaveDateTime())
                .endProcessingDateTime(item.getEndProcessingDateTime())
                .startProcessingDateTime(item.getStartProcessingDateTime())
                .build();
    }

    public Collection<OrderData> convert(InfrastructureClientConverter clientConverter, Collection<OrderModel> items) {
        return items.stream()
                .map(item -> this.convert(clientConverter, item))
                .toList();
    }
}
