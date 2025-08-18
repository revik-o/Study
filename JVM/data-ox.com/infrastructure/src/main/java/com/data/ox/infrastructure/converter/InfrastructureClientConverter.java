package com.data.ox.infrastructure.converter;

import com.data.ox.core.data.ClientData;
import com.data.ox.infrastructure.model.ClientModel;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static java.util.Set.copyOf;

@Service
public class InfrastructureClientConverter {

    public ClientData convert(InfrastructureOrderConverter orderConverter, ClientModel item) {
        return ClientData.builder()
                .id(item.getId())
                .name(item.getName())
                .email(item.getEmail())
                .active(item.isActive())
                .address(item.getAddress())
                .activeDateTime(item.getActiveDateTime())
                .unactiveDateTime(item.getUnactiveDateTime())
                .orders(() -> copyOf(orderConverter.convert(this, item.getOrders())))
                .build();
    }

    public Collection<ClientData> convert(InfrastructureOrderConverter orderConverter, Collection<ClientModel> items) {
        return items.stream()
                .map(item -> this.convert(orderConverter, item))
                .toList();
    }

    public ClientModel convert(ClientData data) {
        return ClientModel.builder()
                .id(data.getId())
                .name(data.getName())
                .email(data.getEmail())
                .address(data.getAddress())
                .active(data.isActive())
                .unactiveDateTime(data.getUnactiveDateTime())
                .activeDateTime(data.getActiveDateTime())
                .build();
    }
}
