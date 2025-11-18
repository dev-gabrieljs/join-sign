package com.br.join_sign.service;

import com.assemblyai.api.AssemblyAI;
import com.assemblyai.api.core.RequestOptions;
import com.assemblyai.api.resources.transcripts.types.Transcript;
import com.assemblyai.api.resources.transcripts.types.Transcript.LanguageModelStage;
import com.assemblyai.api.resources.transcripts.types.TranscriptLanguageCode;
import com.assemblyai.api.resources.transcripts.types.TranscriptOptionalParams;
import com.br.join_sign.service.imp.TranscricaoAudioServiceImp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class TranscricaoAudioService implements TranscricaoAudioServiceImp {

  @Value("${speech.text.chave-api}")
  private String apiKey;

  @Override
  public String transcreverAudio(InputStream audioStream, String fileName) {
    try {
      // Extrai extensão do nome do arquivo (ex: "wav")
      String extensao = "tmp";
      if (fileName != null && fileName.contains(".")) {
        extensao = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
      }

      // Garante sufixo válido
      String sufixo = "." + extensao;

      // Cria arquivo temporário
      Path tempFile = Files.createTempFile("audio-upload-", sufixo);
      Files.copy(audioStream, tempFile, java.nio.file.StandardCopyOption.REPLACE_EXISTING);

      // Inicializa cliente AssemblyAI
      AssemblyAI client = AssemblyAI.builder().apiKey(apiKey).build();

      // Configura transcrição para português (Brasil)
      Transcript transcript = client.transcripts().transcribe(
              tempFile.toFile(),
              TranscriptOptionalParams.builder()
                      .languageCode(TranscriptLanguageCode.PT)   // português
                      .build()
      );



      // Retorna texto transcrito (Optional<String>)
      return transcript.getText().orElse("Transcrição não disponível");

    } catch (IOException e) {
      e.printStackTrace();
      return "Erro ao processar áudio: " + e.getMessage();
    }
  }
}
