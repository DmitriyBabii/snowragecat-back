package com.snowragecat.main.jpa.models.entities;

import com.snowragecat.main.jpa.models.enums.LeadStatus;
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

    private Integer score;
    private String comment;
    private String message;
    private LeadStatus status;

    @OneToOne
    @JoinColumn(name = "lead_id", nullable = false)
    private Lead lead;
}
