package com.br.join_sign.service.imp;

import com.br.join_sign.dto.SinalizacaoMessage;

public interface WebRtcServiceImp {
  void processarSinalizacao(SinalizacaoMessage msg);
}
