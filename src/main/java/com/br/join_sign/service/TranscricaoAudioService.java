package com.br.join_sign.service;

import com.assemblyai.api.AssemblyAI;
import com.assemblyai.api.resources.transcripts.types.Transcript;
import com.assemblyai.api.resources.transcripts.types.TranscriptLanguageCode;
import com.assemblyai.api.resources.transcripts.types.TranscriptOptionalParams;
import com.br.join_sign.dto.response.TextoResponse;
import com.br.join_sign.repository.TextoRepository;
import com.br.join_sign.service.imp.TranscricaoAudioServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class TranscricaoAudioService implements TranscricaoAudioServiceImp {
  private static final Logger log = LoggerFactory.getLogger(TranscricaoAudioService.class);
  private final TextoRepository repository;

  @Value("${speech.text.chave-api}")
  private String apiKey;

  public TranscricaoAudioService(TextoRepository repository) {
    this.repository = repository;
  }

  @Override
  public TextoResponse transcreverAudio(InputStream audioStream, String fileName) {
    log.info("Iniciando transcrição de áudio para o arquivo: {}", fileName);

    try {
      // Extrai a extensão do nome do arquivo
      String extensao = "tmp";
      if (fileName != null && fileName.contains(".")) {
        extensao = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
      }

      log.info("Extensão do arquivo extraída: {}", extensao);

      String sufixo = "." + extensao;

      Path tempFile = Files.createTempFile("audio-upload-", sufixo);

      Files.copy(audioStream, tempFile, java.nio.file.StandardCopyOption.REPLACE_EXISTING);

      AssemblyAI client = AssemblyAI.builder().apiKey(apiKey).build();

      log.info("Iniciando transcrição do áudio com AssemblyAI.");

      Transcript transcript =
          client
              .transcripts()
              .transcribe(
                  tempFile.toFile(),
                  TranscriptOptionalParams.builder()
                      .languageCode(TranscriptLanguageCode.PT)
                      .build());

      String transcricao = transcript.getText().orElse("Transcrição não disponível");

      log.info("Texto transcrito: {}", transcricao);

      TextoResponse response = new TextoResponse();
      response.setTexto(transcricao);
      TextoResponse salvado = repository.save(response);
      log.info("TextoResponse salvado: {}", salvado);
      return salvado;

    } catch (IOException e) {
      log.error("Erro ao processar áudio: {}", e.getMessage(), e);
      TextoResponse errorResponse = new TextoResponse();
      errorResponse.setTexto("Erro ao processar áudio: " + e.getMessage());
      return errorResponse;
    }
  }
}
