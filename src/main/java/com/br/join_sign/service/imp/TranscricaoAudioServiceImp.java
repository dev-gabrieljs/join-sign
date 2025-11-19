package com.br.join_sign.service.imp;

import com.br.join_sign.dto.response.TextoResponse;

import java.io.IOException;
import java.io.InputStream;

public interface TranscricaoAudioServiceImp {
    TextoResponse transcreverAudio(InputStream audioStream, String formato) throws IOException, InterruptedException;
}

