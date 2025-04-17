package com.example.card_manager_app.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "limits")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Limit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "start_limit")
    private LocalDateTime start;

    @Column(name = "end_limit")
    private LocalDateTime end;

    @Column(name = "amount")
    private Long amount;
}
