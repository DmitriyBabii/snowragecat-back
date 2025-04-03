package com.snowragecat.main.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.snowragecat.main.jpa.services.LeadService;
import com.snowragecat.main.jpa.models.dtos.LeadFormRequest;
import com.snowragecat.main.openai.services.OpenAiService;
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
    private final OpenAiService openAiService;

    @PostMapping
    public ResponseEntity<String> evaluateLead(@RequestBody @Valid LeadFormRequest leadFormRequest) throws JsonProcessingException {
//        leadService.saveAndProcessLead(leadFormRequest);
        ObjectMapper objectMapper = new ObjectMapper();
        String process = openAiService.evaluateLead(objectMapper.writeValueAsString(leadFormRequest));
        return ResponseEntity.ok(process);
    }
}
