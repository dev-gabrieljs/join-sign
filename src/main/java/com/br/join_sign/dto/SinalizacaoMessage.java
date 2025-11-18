package com.br.join_sign.dto;

public class SinalizacaoMessage {
  private String salaId;
  private String userId;
  private String targetUserId;
  private String tipo;
  private String payload;

  public SinalizacaoMessage(String salaId, String userId, String targetUserId, String tipo, String payload) {
    this.salaId = salaId;
    this.userId = userId;
    this.targetUserId = targetUserId;
    this.tipo = tipo;
    this.payload = payload;
  }

  public SinalizacaoMessage() {}

  public String getSalaId() {
    return salaId;
  }

  public void setSalaId(String salaId) {
    this.salaId = salaId;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getTargetUserId() {
    return targetUserId;
  }

  public void setTargetUserId(String targetUserId) {
    this.targetUserId = targetUserId;
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public String getPayload() {
    return payload;
  }

  public void setPayload(String payload) {
    this.payload = payload;
  }
}
