package com.data.ox.core.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class CreateClientRequestDTO {

    private String name;
    private String email;
    private String address;
}
