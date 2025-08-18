package com.data.ox.core.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static lombok.Builder.Default;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class OrderData implements Serializable {

    private long id;
    private String name;
    private ClientData supplier;
    private ClientData consumer;
    private LocalDateTime saveDateTime;
    private LocalDateTime endProcessingDateTime;

    @Default
    private BigDecimal amount = new BigDecimal("1");
    @Default
    private LocalDateTime startProcessingDateTime = LocalDateTime.now();
}
