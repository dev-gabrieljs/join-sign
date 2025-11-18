package com.br.join_sign.dto.request;

public class TranscriptionResponse {

  private String id;
  private String status;
  private String text;


  public TranscriptionResponse() {}

  public TranscriptionResponse(String id, String status, String text) {
    this.id = id;
    this.status = status;
    this.text = text;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  // Verifica se a transcrição está completa
  public boolean isCompleted() {
    return "completed".equalsIgnoreCase(status);
  }

  // Verifica se houve falha
  public boolean isFailed() {
    return "failed".equalsIgnoreCase(status);
  }
}
