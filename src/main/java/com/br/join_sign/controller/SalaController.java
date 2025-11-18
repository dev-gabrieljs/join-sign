package com.br.join_sign.controller;

import com.br.join_sign.service.SalaService;
import com.br.join_sign.service.imp.SalaServiceImp;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/sala")
public class SalaController {

  private final SalaServiceImp service;

  public SalaController(SalaServiceImp service) {
    this.service = service;
  }

  // Criar sala
  @PostMapping("/criar")
  public Map<String, String> criarSala() {
    String id = service.criarSala();
    return Map.of("salaId", id);
  }

  // Entrar na sala
  @PostMapping("/entrar/{salaId}/{usuarioId}")
  public Map<String, Object> entrarNaSala(
          @PathVariable String salaId,
          @PathVariable String usuarioId) {

    boolean sucesso = service.entrarNaSala(salaId, usuarioId);

    return Map.of(
            "sucesso", sucesso,
            "usuariosNaSala", service.quantidadeUsuarios(salaId),
            "usuarios", service.getUsuarios(salaId)
    );
  }

  // Verificar se existe
  @GetMapping("/existe/{salaId}")
  public Map<String, Boolean> existe(@PathVariable String salaId) {
    return Map.of("existe", service.salaExiste(salaId));
  }
}
