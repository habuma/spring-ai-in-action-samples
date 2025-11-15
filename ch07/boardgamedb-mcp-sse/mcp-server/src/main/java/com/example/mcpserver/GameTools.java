package com.example.mcpserver;

import org.springaicommunity.mcp.annotation.McpTool;
import org.springaicommunity.mcp.annotation.McpToolParam;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameTools {

  private final GameRepository gameRepository;

  public GameTools(GameRepository gameRepository) { 
    this.gameRepository = gameRepository;
  }

  @McpTool(name = "gameCount", description = "Returns the count of games in the repository.")
  public long gameCount() {
    return gameRepository.count();
  }

  @Tool(name = "findGamesForPlayerCount",
        description = "Finds a games suitable for the specified number of players.")
  public List<Game> findGamesForPlayerCount(
      @McpToolParam(description = "The number of players to find games for.") int numPlayers) {
    return gameRepository.findGamesForPlayerCount(numPlayers);  
  }

  @McpTool(name = "findGamesForPlayingTime",
        description = "Finds games suitable for the specified playing time.")
  public List<Game> findGamesForPlayingTime(
      @McpToolParam(description = "The time for playing the game.") int time) {
    return gameRepository.findGamesForPlayingTime(time);   
  }

}
