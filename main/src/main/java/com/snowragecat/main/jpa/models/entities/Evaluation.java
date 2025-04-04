package com.snowragecat.main.jpa.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.snowragecat.main.jpa.models.enums.LeadStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer score;
    @Column(length = 1000)
    private String comment;
    @Column(length = 1000)
    private String message;
    private LeadStatus status;

    @OneToOne
    @JoinColumn(name = "lead_id", nullable = false)
    @JsonIgnore
    private Lead lead;
}
