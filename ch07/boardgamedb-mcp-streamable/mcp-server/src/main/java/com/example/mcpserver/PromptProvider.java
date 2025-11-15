package com.example.mcpserver;

import io.modelcontextprotocol.spec.McpSchema;
import org.springaicommunity.mcp.annotation.McpArg;
import org.springaicommunity.mcp.annotation.McpPrompt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromptProvider {

  @McpPrompt(
      name = "gamesForPlayerCount",
      description = "A prompt to find games for a specific number of players") 
  public McpSchema.GetPromptResult gamesForPlayerCount(
      @McpArg(name = "playerCount",
              description = "The number of players",
              required = true) Integer playerCount) {  

    var userMessage = new McpSchema.PromptMessage(  
        McpSchema.Role.USER,
        new McpSchema.TextContent(
            String.format("Find games for %s players", playerCount)
        ));

    return new McpSchema.GetPromptResult(
        String.format("A prompt to find games for %s players", playerCount),
        List.of(userMessage));  
  }

  @McpPrompt(
      name = "gamesForPlayingTime",
      description = "A prompt to find games for a specific number of players") 
  public McpSchema.GetPromptResult gamesForPlayingTime(
      @McpArg(name = "timeInMinutes",
          description = "The time in minutes",
          required = true) Integer timeInMinutes) {  

    var userMessage = new McpSchema.PromptMessage( 
        McpSchema.Role.USER,
        new McpSchema.TextContent(
            String.format("Find games to play in %s minutes", timeInMinutes)
        ));

    return new McpSchema.GetPromptResult(
        String.format("A prompt to find games to play in %s minutes", timeInMinutes),
        List.of(userMessage));  
  }
}
