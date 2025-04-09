package com.snowragecat.main.controllers;

import com.snowragecat.main.models.dtos.LeadFormRequest;
import com.snowragecat.main.models.dtos.LeadResponse;
import com.snowragecat.main.services.jpa.LeadService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lead")
@RequiredArgsConstructor
public class LeadController {
    private final LeadService leadService;

    @GetMapping
    public ResponseEntity<List<LeadResponse>> getAll() {
        return ResponseEntity.ok(leadService.findAll());
    }

    @PostMapping
    public ResponseEntity<Void> evaluateLead(@RequestBody @Valid LeadFormRequest leadFormRequest) {
        leadService.saveAndProcessLeadData(leadFormRequest);
        return ResponseEntity.ok().build();
    }
}
