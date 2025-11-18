package com.br.join_sign.service.imp;

import java.util.Set;

public interface SalaServiceImp {
    String criarSala();
    boolean entrarNaSala(String salaId, String usuarioId);
    boolean salaExiste(String salaId);
    int quantidadeUsuarios(String salaId);
    Set<String> getUsuarios(String salaId);
}