package com.data.ox.core.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.function.Supplier;

import static lombok.Builder.Default;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class ClientData implements Serializable {

    @Default
    private long id = -1;
    private String name;
    private String email;
    private String address;
    private LocalDateTime unactiveDateTime;

    @Default
    private boolean active = true;
    @Default
    private LocalDateTime activeDateTime = LocalDateTime.now();

    @Default
    private Supplier<Set<OrderData>> orders = () -> null;

    public static ClientData createLightClientData(long id) {
        var lightData = new ClientData();
        lightData.setId(id);
        return lightData;
    }
}
