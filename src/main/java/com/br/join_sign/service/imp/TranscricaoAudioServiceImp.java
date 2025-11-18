package com.br.join_sign.service.imp;

import java.io.IOException;
import java.io.InputStream;

public interface TranscricaoAudioServiceImp {
    String transcreverAudio(InputStream audioStream, String formato) throws IOException, InterruptedException;
}

