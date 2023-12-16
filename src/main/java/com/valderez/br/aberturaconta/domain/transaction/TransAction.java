package com.valderez.br.aberturaconta.domain.transaction;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name="transactions")
@Table(name="transActions")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of="id")

public class TransAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long Id;
    private BigDecimal amount;
    @ManyToOne
    @JoinColumn(name="sender_id")
    private User sender;
    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiver;
    private LocalDateTime timestamp;
}
