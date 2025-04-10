package com.snowragecat.main.controllers;

import com.snowragecat.main.models.dtos.LeadBody;
import com.snowragecat.main.models.dtos.LeadFormRequest;
import com.snowragecat.main.models.entities.Evaluation;
import com.snowragecat.main.models.entities.Lead;
import com.snowragecat.main.services.jpa.EvaluationService;
import com.snowragecat.main.services.jpa.LeadService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/lead")
@RequiredArgsConstructor
@CrossOrigin("*")
public class LeadController {
    private final EvaluationService evaluationService;
    private final LeadService leadService;

    @GetMapping
    public ResponseEntity<Page<LeadBody>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        Pageable pageable = PageRequest.of(page, size);
        Page<LeadBody> leadPage = leadService.findAll(pageable);

        return ResponseEntity.ok()
                .header(
                        "Content-Range",
                        String.format("clients %d-%d/%d",
                                page * size,
                                page * size + leadPage.getNumberOfElements() - 1,
                                leadPage.getTotalElements())
                )
                .header("Access-Control-Expose-Headers", "Content-Range")
                .body(leadPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lead> getAll(@PathVariable Long id) {
        return ResponseEntity.of(leadService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LeadBody> update(@PathVariable Long id, @RequestBody LeadBody leadBody) {
        Lead lead = leadService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(400)));

        Evaluation evaluation = lead.getEvaluation();
        evaluation.setScore(leadBody.getEvaluation().getScore());
        evaluation.setStatus(leadBody.getEvaluation().getStatus());

        evaluationService.save(evaluation);
        return ResponseEntity.ok(leadBody);
    }

    @PostMapping
    public ResponseEntity<Void> evaluateLead(@RequestBody @Valid LeadFormRequest leadFormRequest) {
        leadService.saveAndProcessLeadData(leadFormRequest);
        return ResponseEntity.ok().build();
    }
}
