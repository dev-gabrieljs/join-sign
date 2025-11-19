package com.br.join_sign.repository;

import com.br.join_sign.dto.response.TextoResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TextoRepository extends JpaRepository<TextoResponse, Long> {}
