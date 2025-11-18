package com.br.join_sign.service;

import com.br.join_sign.dto.SinalizacaoMessage;
import com.br.join_sign.service.imp.SalaServiceImp;
import com.br.join_sign.service.imp.WebRtcServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebRtcService implements WebRtcServiceImp {

  private static final Logger log = LoggerFactory.getLogger(WebRtcService.class);

  private final SalaServiceImp salaService;
  private final SimpMessagingTemplate messagingTemplate;

  public WebRtcService(SalaServiceImp salaService, SimpMessagingTemplate messagingTemplate) {
    this.salaService = salaService;
    this.messagingTemplate = messagingTemplate;
  }

  @Override
  public void processarSinalizacao(SinalizacaoMessage msg) {
    String salaId = msg.getSalaId();
    String userId = msg.getUserId();
    String targetId = msg.getTargetUserId();

    if (!salaService.salaExiste(salaId) || !salaService.getUsuarios(salaId).contains(userId)) {
      log.warn("Usuário {} tentou sinalizar sem estar na sala {}", userId, salaId);
      return;
    }

    log.info(
        "Sinalização recebida: sala={}, tipo={}, de={}, para={}",
        salaId,
        msg.getTipo(),
        userId,
        targetId);

    if ("join".equals(msg.getTipo())) {
      for (String usuario : salaService.getUsuarios(salaId)) {
        if (!usuario.equals(userId)) {
          String destino = "/topic/room." + salaId + "." + usuario;
          messagingTemplate.convertAndSend(destino, msg);
        }
      }
      return;
    }

    if (targetId != null && !targetId.isBlank()) {
      String destino = "/topic/room." + salaId + "." + targetId;
      messagingTemplate.convertAndSend(destino, msg);
    }
  }
}
