package com.snowragecat.main.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // TODO check for 0 to 100 value
    private Integer score;
    private String comment;

    @OneToOne
    @JoinColumn(name = "lead_id", nullable = false)
    private Lead lead;
}
