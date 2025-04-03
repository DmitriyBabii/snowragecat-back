package com.snowragecat.openai.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ChatRequest {
    private String model;
    private List<ChatMessage> messages;
    private double temperature;
    @JsonProperty("max_tokens")
    private int maxTokens;
}
