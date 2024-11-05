package io.hacken.task.database.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;

import java.io.Serializable;
import java.time.LocalDateTime;

import static org.hibernate.type.SqlTypes.LONGVARCHAR;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accepted_transactions")
public class AcceptedTransaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @JdbcTypeCode(LONGVARCHAR)
    @Column(name = "sent_to")
    private String sentTo;
    @JdbcTypeCode(LONGVARCHAR)
    @Column(name = "sent_from")
    private String sentFrom;
    @JdbcTypeCode(LONGVARCHAR)
    @Column(name = "gas")
    private String gas;
    @JdbcTypeCode(LONGVARCHAR)
    @Column(name = "gas_price")
    private String gasPrice;
    @JdbcTypeCode(LONGVARCHAR)
    @Column(name = "block_number")
    private String blockNumber;
    @JdbcTypeCode(LONGVARCHAR)
    @Column(name = "transaction_hash", nullable = false)
    private String transactionHash;
    @Column(name = "date", nullable = false)
    private LocalDateTime date;
}
