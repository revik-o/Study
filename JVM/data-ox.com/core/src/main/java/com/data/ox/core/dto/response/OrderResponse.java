package com.data.ox.core.dto.response;

import com.data.ox.core.data.ClientData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse implements Serializable {

    private long id;
    private String name;
    private BigDecimal amount;
    private ClientData supplier;
    private ClientData consumer;
    private LocalDateTime saveDateTime;
    private LocalDateTime endProcessingDateTime;
    private LocalDateTime startProcessingDateTime;
}
