package com.contactlist.automation.api;

import com.contactlist.automation.models.Contacto;
import com.contactlist.automation.models.UsuarioContactList;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ContactListApi {

    private static final String BASE_URL;

    static {
        String url = System.getProperty("contactlist.base.url");
        if (url == null || url.isEmpty()) {
            url = System.getenv("CONTACTLIST_BASE_URL");
        }
        if (url == null || url.isEmpty()) {
            url = "https://thinking-tester-contact-list.herokuapp.com";
        }
        BASE_URL = url;
        System.setProperty("java.net.preferIPv4Stack", "true");
    }

    public Response crearUsuario(UsuarioContactList usuario) {
        return given()
                .baseUri(BASE_URL)
                .contentType("application/json")
                .body(usuarioPayload(usuario))
                .when()
                .post("/users");
    }

    public Response iniciarSesion(UsuarioContactList usuario) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("email", usuario.getEmail());
        payload.put("password", usuario.getPassword());

        return given()
                .baseUri(BASE_URL)
                .contentType("application/json")
                .body(payload)
                .when()
                .post("/users/login");
    }

    public Response crearContacto(String token, Contacto contacto) {
        return given()
                .baseUri(BASE_URL)
                .contentType("application/json")
                .header("Authorization", "Bearer " + token)
                .body(contactoPayload(contacto))
                .when()
                .post("/contacts");
    }

    public Response crearContacto(String token, Map<String, Object> contacto) {
        return given()
                .baseUri(BASE_URL)
                .contentType("application/json")
                .header("Authorization", "Bearer " + token)
                .body(contacto)
                .when()
                .post("/contacts");
    }

    public Response consultarContacto(String token, String contactId) {
        return given()
                .baseUri(BASE_URL)
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/contacts/" + contactId);
    }

    public Response actualizarTelefono(String token, String contactId, String nuevoTelefono) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("phone", nuevoTelefono);

        return given()
                .baseUri(BASE_URL)
                .contentType("application/json")
                .header("Authorization", "Bearer " + token)
                .body(payload)
                .when()
                .patch("/contacts/" + contactId);
    }

    public Response eliminarContacto(String token, String contactId) {
        return given()
                .baseUri(BASE_URL)
                .header("Authorization", "Bearer " + token)
                .when()
                .delete("/contacts/" + contactId);
    }

    private Map<String, Object> usuarioPayload(UsuarioContactList usuario) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("firstName", usuario.getFirstName());
        payload.put("lastName", usuario.getLastName());
        payload.put("email", usuario.getEmail());
        payload.put("password", usuario.getPassword());
        return payload;
    }

    public Map<String, Object> contactoPayload(Contacto contacto) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("firstName", contacto.getFirstName());
        payload.put("lastName", contacto.getLastName());
        payload.put("birthdate", contacto.getBirthdate());
        payload.put("email", contacto.getEmail());
        payload.put("phone", contacto.getPhone());
        payload.put("street1", contacto.getStreet1());
        payload.put("street2", contacto.getStreet2());
        payload.put("city", contacto.getCity());
        payload.put("stateProvince", contacto.getStateProvince());
        payload.put("postalCode", contacto.getPostalCode());
        payload.put("country", contacto.getCountry());
        return payload;
    }
}
