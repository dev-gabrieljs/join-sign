package com.br.join_sign.controller;

import com.br.join_sign.dto.SinalizacaoMessage;
import com.br.join_sign.service.imp.WebRtcServiceImp;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

@Controller
public class WebRtcController {

  private final WebRtcServiceImp webRtcService;

  public WebRtcController(WebRtcServiceImp webRtcService) {
    this.webRtcService = webRtcService;
  }

  @MessageMapping("/sinalizar")
  public void sinalizar(@Payload SinalizacaoMessage msg) {
    webRtcService.processarSinalizacao(msg);
  }
}
