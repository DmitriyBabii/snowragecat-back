package com.snowragecat.main.controllers;

import com.snowragecat.main.jpa.services.LeadService;
import com.snowragecat.main.models.dtos.LeadFormRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lead")
@RequiredArgsConstructor
public class LeadController {
    private final LeadService leadService;

    @PostMapping
    public ResponseEntity<Void> evaluateLead(@RequestBody @Valid LeadFormRequest leadFormRequest) {
        leadService.saveAndProcessLead(leadFormRequest);
        return ResponseEntity.ok().build();
    }
}
