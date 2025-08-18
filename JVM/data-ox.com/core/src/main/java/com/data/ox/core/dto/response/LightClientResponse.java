package com.data.ox.core.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LightClientResponse implements Serializable {

    private long id;
    private String name;
    private String email;
    private String address;
    private boolean active;
    private LocalDateTime activeDateTime;
    private LocalDateTime unactiveDateTime;
}
