package com.data.ox.core.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class CreateOrderRequestDTO {

    private String name;
    private long supplierId;
    private long consumerId;
    private BigDecimal amount;
}
