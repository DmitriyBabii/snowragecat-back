package com.snowragecat.main.models.entities;

import com.snowragecat.main.models.enums.LeadStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Verdict {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private LeadStatus status;

    @OneToOne
    @JoinColumn(name = "lead_id", nullable = false)
    private Lead lead;
}
