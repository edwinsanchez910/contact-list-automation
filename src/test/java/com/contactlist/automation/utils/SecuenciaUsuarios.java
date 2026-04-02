package com.contactlist.automation.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public final class SecuenciaUsuarios {

    private static final Path SEQUENCE_FILE = Path.of(".test-data", "contact-list-user-sequence.txt");

    private SecuenciaUsuarios() {
    }

    public static synchronized String siguienteUsuarioEdwin() {
        int siguiente = leerConsecutivoActual() + 1;
        guardarConsecutivo(siguiente);
        return String.format("edwin_sanchez%02d", siguiente);
    }

    private static int leerConsecutivoActual() {
        if (!Files.exists(SEQUENCE_FILE)) {
            return 0;
        }

        try {
            String valor = Files.readString(SEQUENCE_FILE, StandardCharsets.UTF_8).trim();
            return valor.isEmpty() ? 0 : Integer.parseInt(valor);
        } catch (IOException | NumberFormatException exception) {
            return 0;
        }
    }

    private static void guardarConsecutivo(int consecutivo) {
        try {
            Files.createDirectories(SEQUENCE_FILE.getParent());
            Files.writeString(SEQUENCE_FILE, String.valueOf(consecutivo), StandardCharsets.UTF_8);
        } catch (IOException exception) {
            throw new IllegalStateException("No fue posible actualizar el consecutivo local de usuarios", exception);
        }
    }
}
