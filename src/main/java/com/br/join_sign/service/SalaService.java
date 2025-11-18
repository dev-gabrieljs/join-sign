package com.br.join_sign.service;

import com.br.join_sign.service.imp.SalaServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SalaService implements SalaServiceImp {

  private static final Logger log = LoggerFactory.getLogger(SalaService.class);
  private final Map<String, Set<String>> salas = new HashMap<>();

  @Override
  public synchronized String criarSala() {
    String id = UUID.randomUUID().toString().substring(0, 8);

    salas.put(id, new HashSet<>());

    log.info("Sala criada com ID: {}", id);

    return id;
  }

  @Override
  public synchronized boolean entrarNaSala(String salaId, String usuarioId) {
    log.info("Tentando adicionar usuário '{}' na sala '{}'", usuarioId, salaId);

    if (!salas.containsKey(salaId)) {
      log.info("Falha ao adicionar usuário: sala '{}' não existe", salaId);
      return false;
    }

    Set<String> usuarios = salas.get(salaId);

    if (usuarios.contains(usuarioId)) {
      log.info("Usuário '{}' já está na sala '{}'", usuarioId, salaId);
      return true;
    }

    if (usuarios.size() >= 2) {
      log.info("Falha ao adicionar usuário: sala '{}' já está cheia", salaId);
      return false;
    }

    usuarios.add(usuarioId);
    log.info("Usuário '{}' foi adicionado na sala '{}'", usuarioId, salaId);
    return true;
  }

  @Override
  public synchronized boolean salaExiste(String salaId) {
    boolean existe = salas.containsKey(salaId);

    log.info("Verificação da sala '{}': existe = {}", salaId, existe);

    return existe;
  }

  @Override
  public synchronized int quantidadeUsuarios(String salaId) {
    int qtd = salas.getOrDefault(salaId, Set.of()).size();

    log.info("Quantidade de usuários na sala '{}': {}", salaId, qtd);

    return qtd;
  }

  @Override
  public synchronized Set<String> getUsuarios(String salaId) {
    Set<String> usuarios = salas.getOrDefault(salaId, Set.of());

    log.info("Listando usuários da sala '{}': {}", salaId, usuarios);

    return usuarios;
  }
}
