package com.contactlist.automation.utils;

import com.contactlist.automation.models.Contacto;
import com.contactlist.automation.models.UsuarioContactList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class DatosDinamicos {

    private DatosDinamicos() {
    }

    public static UsuarioContactList usuarioValido() {
        String usuario = SecuenciaUsuarios.siguienteUsuarioEdwin();
        return new UsuarioContactList("Edwin", "Sanchez", usuario + "@test.com", "Albeiro12345");
    }

    public static UsuarioContactList usuarioConEmailBase(String firstName, String lastName, String emailBase,
                                                         String password) {
        return new UsuarioContactList(firstName, lastName, emailDinamico(emailBase), password);
    }

    public static Contacto contactoValido() {
        String stamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HHmmssSSS"));
        return new Contacto(
                "Carlos",
                "Restrepo" + stamp,
                "1990-05-12",
                "carlos.restrepo." + stamp + "@test.com",
                "300555" + stamp.substring(stamp.length() - 4),
                "Calle 10 # 20-30",
                "Apto 401",
                "Medellin",
                "Antioquia",
                "050021",
                "Colombia"
        );
    }

    private static String emailDinamico(String emailBase) {
        if (emailBase == null || emailBase.isBlank() || !emailBase.contains("@")) {
            return emailBase;
        }

        String stamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HHmmssSSS"));
        int at = emailBase.indexOf('@');
        return emailBase.substring(0, at) + "." + stamp + emailBase.substring(at);
    }
}
