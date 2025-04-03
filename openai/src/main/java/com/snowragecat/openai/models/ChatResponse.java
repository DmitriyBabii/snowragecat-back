package com.snowragecat.openai.models;

import java.util.List;

public record ChatResponse(List<ChatChoice> choices) {
}
