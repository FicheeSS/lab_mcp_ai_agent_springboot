package com.theobocquet.mcp_agent.config;

import com.theobocquet.mcp_agent.agent.BacklogAgent;
import com.theobocquet.mcp_agent.tools.AgentTool;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel; // Import Gemini model
// import dev.langchain4j.model.anthropic.AnthropicChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.List;

@Configuration
public class LangChainConfig {

    @Bean
    public GoogleAiGeminiChatModel googleAiGeminiChatModel(
            @Value("${gemini.api-key}") String apiKey,
            @Value("${gemini.model}") String model,
            @Value("${gemini.timeout-seconds:60}") Integer timeoutSeconds
    ) {
        return GoogleAiGeminiChatModel.builder()
                .apiKey(apiKey)
                .modelName(model) // e.g., gemini-2.0-flash
                .timeout(Duration.ofSeconds(timeoutSeconds))
                .logRequestsAndResponses(true) // Helpful for debugging
                .build();
    }

    @Bean
    public BacklogAgent backlogAgent(GoogleAiGeminiChatModel model, List<AgentTool> tools) {
        // NOTE: I changed the parameter type from 'AnthropicChatModel' to 'ChatLanguageModel'
        // This makes your agent agnostic to the underlying provider (Gemini/OpenAI/Anthropic).

        System.out.println("=== Agent tools loaded: " + tools.size() + " ===");
        tools.forEach(t -> System.out.println(" - " + t.getClass().getName()));

        return AiServices.builder(BacklogAgent.class)
                .chatModel(model)
                .tools(tools.toArray())
                .build();
    }
}