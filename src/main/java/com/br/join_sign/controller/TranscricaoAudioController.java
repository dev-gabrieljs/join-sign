package com.br.join_sign.controller;

import com.br.join_sign.service.imp.TranscricaoAudioServiceImp;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/audio")
public class TranscricaoAudioController {

  private final TranscricaoAudioServiceImp transcricaoAudioServiceImp;

  public TranscricaoAudioController(TranscricaoAudioServiceImp transcricaoAudioServiceImp) {
    this.transcricaoAudioServiceImp = transcricaoAudioServiceImp;
  }

  @PostMapping(value= "/transcrever", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<String> transcrever(@RequestParam("file") MultipartFile file) throws IOException, InterruptedException {
      String texto =
          transcricaoAudioServiceImp.transcreverAudio(file.getInputStream(), file.getContentType());
      return ResponseEntity.ok(texto);
  }
}
