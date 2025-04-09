package com.snowragecat.main.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
public class Lead {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String company;
    private BigDecimal budget;

    @Column(length = 2000)
    private String message;

    @Column(updatable = false)
    @CreationTimestamp
    private Timestamp created;

    @OneToOne(mappedBy = "lead", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Evaluation evaluation;
}
