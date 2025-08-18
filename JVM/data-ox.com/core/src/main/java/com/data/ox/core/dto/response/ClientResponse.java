package com.data.ox.core.dto.response;

import com.data.ox.core.data.OrderData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

import static java.util.Collections.emptySet;

@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponse implements Serializable {

    private LightClientResponse clientData;
    private Set<OrderData> orders = emptySet();
}
