package com.br.join_sign.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "google-speech-api", url = "https://speech.googleapis.com/v1/speech:recognize")
public interface GoogleFeign {
  @PostMapping
  String transcribe(@RequestBody String audioData);
}
