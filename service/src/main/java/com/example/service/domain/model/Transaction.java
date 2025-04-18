package com.example.service.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "transactions")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "from_id")
    private Card from;

    @ManyToOne
    @JoinColumn(name = "to_id")
    private Card to;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TransactionType type;
}
