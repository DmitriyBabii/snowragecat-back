package com.snowragecat.main.openai.services;

import com.snowragecat.main.openai.models.ChatMessage;
import com.snowragecat.main.openai.models.ChatRequest;
import com.snowragecat.main.openai.models.ChatResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class OpenAiService {
    public static final double TEMPERATURE = .1;
    public static final int MAX_TOKENS = 200;
    private static final String SYSTEM_PROMPT = """
            You are a system that scores potential clients for an advertising agency based on the data they provide. Your task is to assess the quality of the client and create a personalized message.
                        
            Data: {name:string;email:string;company:string;budget:double;message:string;}
                        
            Based on this data, answer in the following format:
            {score:integer;message:string;comment:string;}
                        
            `score`: The client's score from 0 to 100, based on these factors:
            1. **Budget**: if the budget is large, the score is higher.
            2. **Company**: the size and type of company can affect the potential for cooperation (for example, large companies with high budgets have more potential).
            3. **Message**: If the message is detailed and describes clear goals, it can increase the score.
                        
            `comment`: A message that tells why is there such score
                        
            `message`: A personalized message to the client that also indicates the potential success of the collaboration, but does not report willingness to cooperate.
                 
            Use these principles to formulate your response.
            """;


    @Value("${openai.api.model}")
    private String model;
    @Value("${openai.api.url}")
    private String url;
    @Value("${openai.api.key}")
    private String key;


    private final RestTemplate restTemplate;


    public String evaluateLead(String leadData) {
        ChatRequest chatRequest = new ChatRequest(model, getMessages(leadData), TEMPERATURE, MAX_TOKENS);
        HttpEntity<ChatRequest> httpEntity = new HttpEntity<>(chatRequest, getHeaders());
        ResponseEntity<ChatResponse> exchange = restTemplate.exchange(
                url,
                HttpMethod.POST,
                httpEntity,
                ChatResponse.class
        );

        return Objects.requireNonNull(exchange.getBody())
                .choices()
                .getFirst()
                .message()
                .getContent();
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Bearer " + key);
        return headers;
    }

    private static List<ChatMessage> getMessages(String leadData) {
        return List.of(
                new ChatMessage("system", SYSTEM_PROMPT),
                new ChatMessage("user", leadData)
        );
    }
}
