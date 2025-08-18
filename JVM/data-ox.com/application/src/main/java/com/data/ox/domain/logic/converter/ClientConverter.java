package com.data.ox.domain.logic.converter;

import com.data.ox.core.data.ClientData;
import com.data.ox.core.dto.response.ClientResponse;
import com.data.ox.core.dto.response.LightClientResponse;
import lombok.NonNull;

import java.util.Collection;

public class ClientConverter {

    public final LightClientResponse convertToLight(@NonNull ClientData item) {
        return LightClientResponse.builder()
                .activeDateTime(item.getActiveDateTime())
                .unactiveDateTime(item.getUnactiveDateTime())
                .address(item.getAddress())
                .active(item.isActive())
                .email(item.getEmail())
                .name(item.getName())
                .id(item.getId())
                .build();
    }

    public final Collection<LightClientResponse> convertToLight(@NonNull Collection<ClientData> items) {
        return items.stream()
                .map(this::convertToLight)
                .toList();
    }

    public final ClientResponse convert(ClientData item) {
        return ClientResponse.builder()
                .clientData(this.convertToLight(item))
                .orders(item.getOrders().get())
                .build();
    }
}
