package com.example.mcpserver;

import io.modelcontextprotocol.spec.McpSchema;
import org.springaicommunity.mcp.annotation.McpResource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceProvider {

  private final GameRepository gameRepository;

  public ResourceProvider(GameRepository gameRepository) {
    this.gameRepository = gameRepository;
  }

  @McpResource(uri = "games://game-list",
               name = "Game List",
               description = "A list of games available in the repository")
  public McpSchema.ReadResourceResult gameListResource(McpSchema.ReadResourceRequest request) {
    var gameTitles = gameRepository.findAllTitles();
    var gameListText = new StringBuilder();
    for (String title : gameTitles) {
      gameListText.append("- ").append(title).append("\n");
    }

    return new McpSchema.ReadResourceResult(
        List.of(new McpSchema.TextResourceContents(
            request.uri(),
            "text/plain",
            gameListText.toString())));
  }

}
