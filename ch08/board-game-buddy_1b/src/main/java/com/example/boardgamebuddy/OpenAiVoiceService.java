

package com.example.boardgamebuddy;

import org.springframework.ai.audio.tts.TextToSpeechModel;
import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class OpenAiVoiceService implements VoiceService {

  private final OpenAiAudioTranscriptionModel transcriptionModel;
  
  private final TextToSpeechModel speechModel;
  

  
  
  /*
  
  public OpenAiVoiceService(
      OpenAiAudioTranscriptionModel transcriptionModel) {
    this.transcriptionModel = transcriptionModel; 
  }
  
  */


  public OpenAiVoiceService(
      OpenAiAudioTranscriptionModel transcriptionModel,
      TextToSpeechModel speechModel) {
    this.transcriptionModel = transcriptionModel;
    this.speechModel = speechModel;
  }
  

  /*
  

  ...


   */

  

  @Override
  public String transcribe(Resource audioFileResource) {
    return transcriptionModel.call(audioFileResource); 
  }

  

  /*

  
  @Override
  public byte[] textToSpeech(String text) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  
  */

  
  @Override
  public Resource textToSpeech(String text) {
    var speechBytes = speechModel.call(text);
    return new ByteArrayResource(speechBytes);
  }
  

  

}



