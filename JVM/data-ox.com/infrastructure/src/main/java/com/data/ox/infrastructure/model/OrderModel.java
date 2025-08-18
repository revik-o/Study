package com.data.ox.infrastructure.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.springframework.stereotype.Indexed;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.Builder.Default;
import static org.hibernate.type.SqlTypes.LONGVARCHAR;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_table", indexes = {
        @Index(name = "idx_order_name", columnList = "name"),
        @Index(name = "idx_order_amount", columnList = "amount"),
        @Index(name = "idx_order_search", columnList = "search"),
})
public class OrderModel implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    @Column(name = "name", nullable = false)
    @JdbcTypeCode(LONGVARCHAR)
    private String name;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(nullable = false, name = "supplier_id")
    private ClientModel supplier;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(nullable = false, name = "consumer_id")
    private ClientModel consumer;
    @Default
    @Column(name = "amount", nullable = false)
    private BigDecimal amount = new BigDecimal("1");
    @Default
    @Column(name = "save_date_time", nullable = false)
    private LocalDateTime saveDateTime = LocalDateTime.now();
    @Column(name = "end_processing_date_time", nullable = false)
    private LocalDateTime endProcessingDateTime;
    @Column(name = "start_processing_date_time", nullable = false)
    private LocalDateTime startProcessingDateTime;
    @Column(name = "search", nullable = false)
    private String search;
}
