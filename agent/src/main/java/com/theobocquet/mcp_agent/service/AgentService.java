package com.theobocquet.mcp_agent.service;

import com.theobocquet.mcp_agent.agent.BacklogAgent;
import org.springframework.stereotype.Service;

import org.springframework.stereotype.Service;

@Service
public class AgentService {
    private final BacklogAgent backlogAgent;

    public AgentService(BacklogAgent backlogAgent) {
        this.backlogAgent = backlogAgent;
    }

    public String run(String prompt) {
        return backlogAgent.handle(prompt);
    }
}