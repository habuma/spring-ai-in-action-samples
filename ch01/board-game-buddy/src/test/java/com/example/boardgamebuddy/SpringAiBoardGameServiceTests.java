
package com.example.boardgamebuddy;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.evaluation.RelevancyEvaluator;

import org.springframework.ai.chat.evaluation.FactCheckingEvaluator;

import org.springframework.ai.evaluation.EvaluationRequest;
import org.springframework.ai.evaluation.EvaluationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SpringAiBoardGameServiceTests {

  @Autowired
  private BoardGameService boardGameService;  

  @Autowired
  private ChatClient.Builder chatClientBuilder; 

  private RelevancyEvaluator relevancyEvaluator;

  
  
  private FactCheckingEvaluator factCheckingEvaluator;

  
  @BeforeEach
  public void setup() {
    this.relevancyEvaluator = new RelevancyEvaluator(chatClientBuilder);
    
    this.factCheckingEvaluator = FactCheckingEvaluator.builder(chatClientBuilder)
        .build();
    
  }
  

  @Test
  public void evaluateRelevancy() {
    String userText = "Why is the sky blue?";
    Question question = new Question(userText);
    Answer answer = boardGameService.askQuestion(question); 

    EvaluationRequest evaluationRequest = new EvaluationRequest(
        userText, answer.answer());

    EvaluationResponse response = relevancyEvaluator
        .evaluate(evaluationRequest); 

    Assertions.assertThat(response.isPass())   
        .withFailMessage("""
          ========================================
          The answer "%s"
          is not considered relevant to the question
          "%s".
          ========================================
          """, answer.answer(), userText)
        .isTrue();
  }

  
  
  @Test
  public void evaluateFactualAccuracy() {
    var userText = "Why is the sky blue?";
    var question = new Question(userText);
    var answer = boardGameService.askQuestion(question);

    var evaluationRequest =
            new EvaluationRequest(userText, answer.answer());

    var response =
            factCheckingEvaluator.evaluate(evaluationRequest);

    Assertions.assertThat(response.isPass())
        .withFailMessage("""

          ========================================
          The answer "%s"
          is not considered correct for the question
          "%s".
          ========================================
          """, answer.answer(), userText)
        .isTrue();
  }
  


}

