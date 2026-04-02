package com.contactlist.automation.stepdefinitions;

import com.contactlist.automation.api.ContactListApi;
import com.contactlist.automation.models.Contacto;
import com.contactlist.automation.models.UsuarioContactList;
import com.contactlist.automation.utils.DatosDinamicos;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.restassured.response.Response;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class ContactListApiStepDefinitions {

    private final ContactListApi api = new ContactListApi();
    private UsuarioContactList usuario;
    private Contacto contacto;
    private String token;
    private String contactId;
    private String telefonoActualizado;
    private Response ultimaRespuesta;

    @Cuando("registra por API el usuario con firstName {string}, lastName {string}, email base {string} y password {string}")
    public void registraPorApiElUsuarioConDatos(String firstName, String lastName, String emailBase, String password) {
        usuario = DatosDinamicos.usuarioConEmailBase(firstName, lastName, emailBase, password);
        ultimaRespuesta = api.crearUsuario(usuario);
    }

    @Entonces("el usuario debe crearse correctamente por API")
    public void elUsuarioDebeCrearseCorrectamentePorApi() {
        assertThat(ultimaRespuesta.statusCode(), equalTo(201));
        assertThat(ultimaRespuesta.jsonPath().getString("token"), notNullValue());
        assertThat(ultimaRespuesta.jsonPath().getString("user.email"), equalTo(usuario.getEmail()));
    }

    @Entonces("la API debe rechazar la solicitud con validacion para {string}")
    public void laApiDebeRechazarLaSolicitudConValidacionPara(String campo) {
        assertThat(ultimaRespuesta.statusCode(), equalTo(400));
        assertThat(ultimaRespuesta.asString().toLowerCase(), containsString(campo.toLowerCase()));
    }

    @Dado("que existe un usuario creado por API")
    public void queExisteUnUsuarioCreadoPorApi() {
        usuario = DatosDinamicos.usuarioValido();
        ultimaRespuesta = api.crearUsuario(usuario);

        assertThat(ultimaRespuesta.statusCode(), equalTo(201));
        assertThat(ultimaRespuesta.jsonPath().getString("token"), notNullValue());
    }

    @Cuando("inicia sesion por API con sus credenciales")
    public void iniciaSesionPorApiConSusCredenciales() {
        ultimaRespuesta = api.iniciarSesion(usuario);
    }

    @Entonces("debe recibir acceso exitoso por API")
    public void debeRecibirAccesoExitosoPorApi() {
        assertThat(ultimaRespuesta.statusCode(), equalTo(200));
        assertThat(ultimaRespuesta.jsonPath().getString("token"), notNullValue());
        assertThat(ultimaRespuesta.jsonPath().getString("user.email"), equalTo(usuario.getEmail()));
    }

    @Dado("que el cliente API esta autenticado")
    public void queElClienteApiEstaAutenticado() {
        usuario = DatosDinamicos.usuarioValido();
        contacto = DatosDinamicos.contactoValido();
        ultimaRespuesta = api.crearUsuario(usuario);

        assertThat(ultimaRespuesta.statusCode(), equalTo(201));
        token = ultimaRespuesta.jsonPath().getString("token");
        assertThat(token, notNullValue());
    }

    @Cuando("crea por API un contacto con firstName {string}")
    public void creaPorApiUnContactoConFirstName(String firstName) {
        Map<String, Object> payload = api.contactoPayload(contacto);
        payload.put("firstName", firstName);

        ultimaRespuesta = api.crearContacto(token, payload);
    }

    @Cuando("crea por API un contacto con email {string}")
    public void creaPorApiUnContactoConEmail(String email) {
        Map<String, Object> payload = api.contactoPayload(contacto);
        payload.put("email", email);

        ultimaRespuesta = api.crearContacto(token, payload);
    }

    @Dado("que el cliente API crea un usuario nuevo")
    public void queElClienteApiCreaUnUsuarioNuevo() {
        usuario = DatosDinamicos.usuarioValido();
        contacto = DatosDinamicos.contactoValido();

        ultimaRespuesta = api.crearUsuario(usuario);

        assertThat(ultimaRespuesta.statusCode(), equalTo(201));
        token = ultimaRespuesta.jsonPath().getString("token");
        assertThat(token, notNullValue());
    }

    @Cuando("crea un contacto por API")
    public void creaUnContactoPorApi() {
        ultimaRespuesta = api.crearContacto(token, contacto);

        assertThat(ultimaRespuesta.statusCode(), equalTo(201));
        contactId = ultimaRespuesta.jsonPath().getString("_id");
        assertThat(contactId, notNullValue());
    }

    @Cuando("consulta el contacto por API")
    public void consultaElContactoPorApi() {
        ultimaRespuesta = api.consultarContacto(token, contactId);

        assertThat(ultimaRespuesta.statusCode(), equalTo(200));
        assertThat(ultimaRespuesta.jsonPath().getString("firstName"), equalTo(contacto.getFirstName()));
        assertThat(ultimaRespuesta.jsonPath().getString("lastName"), equalTo(contacto.getLastName()));
    }

    @Cuando("actualiza el telefono del contacto por API")
    public void actualizaElTelefonoDelContactoPorApi() {
        telefonoActualizado = "3109990000";
        ultimaRespuesta = api.actualizarTelefono(token, contactId, telefonoActualizado);

        assertThat(ultimaRespuesta.statusCode(), equalTo(200));
    }

    @Entonces("la API debe retornar el contacto con el telefono actualizado")
    public void laApiDebeRetornarElContactoConElTelefonoActualizado() {
        ultimaRespuesta = api.consultarContacto(token, contactId);

        assertThat(ultimaRespuesta.statusCode(), equalTo(200));
        assertThat(ultimaRespuesta.jsonPath().getString("phone"), equalTo(telefonoActualizado));
    }

    @Cuando("elimina el contacto por API")
    public void eliminaElContactoPorApi() {
        ultimaRespuesta = api.eliminarContacto(token, contactId);

        assertThat(ultimaRespuesta.statusCode(), equalTo(200));
    }

    @Entonces("el contacto no debe estar disponible por API")
    public void elContactoNoDebeEstarDisponiblePorApi() {
        ultimaRespuesta = api.consultarContacto(token, contactId);

        assertThat(ultimaRespuesta.statusCode(), equalTo(404));
    }
}
