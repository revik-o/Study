package com.data.ox.infrastructure.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static java.util.Collections.emptySet;
import static lombok.Builder.Default;
import static org.hibernate.type.SqlTypes.LONGVARCHAR;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "client_table", indexes = {
        @Index(name = "idx_client_name", columnList = "name"),
        @Index(name = "idx_client_email", columnList = "email"),
        @Index(name = "idx_client_search", columnList = "search"),
})
public class ClientModel implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    @Column(name = "name", nullable = false)
    @JdbcTypeCode(LONGVARCHAR)
    private String name;
    @Column(name = "email", nullable = false)
    @JdbcTypeCode(LONGVARCHAR)
    private String email;
    @Column(name = "address", nullable = false)
    @JdbcTypeCode(LONGVARCHAR)
    private String address;
    @Default
    @Column(name = "active", nullable = false)
    private boolean active = true;
    @Column(name = "unactive_date_time")
    private LocalDateTime unactiveDateTime;
    @Default
    @Column(name = "active_date_time", nullable = false)
    private LocalDateTime activeDateTime = LocalDateTime.now();
    @Column(name = "search", nullable = false)
    private String search;

    @Default
    @OneToMany(fetch = LAZY, mappedBy = "consumer_id")
    private Set<OrderModel> orders = emptySet();
}
