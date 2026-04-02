package com.contactlist.automation.stepdefinitions;

import com.contactlist.automation.models.Contacto;
import com.contactlist.automation.models.UsuarioContactList;
import com.contactlist.automation.questions.ContactoVisible;
import com.contactlist.automation.tasks.AbrirContactList;
import com.contactlist.automation.tasks.CerrarSesionContactList;
import com.contactlist.automation.tasks.CrearContactoUi;
import com.contactlist.automation.tasks.IniciarSesionContactList;
import com.contactlist.automation.tasks.RegistrarUsuarioContactList;
import com.contactlist.automation.utils.DatosDinamicos;
import io.cucumber.java.Before;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import static org.hamcrest.Matchers.is;

public class ContactListUiStepDefinitions {

    private UsuarioContactList usuario;
    private Contacto contacto;

    @Before
    public void setStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Dado("que Edwin abre la aplicacion Contact List")
    public void queEdwinAbreLaAplicacionContactList() {
        usuario = DatosDinamicos.usuarioValido();
        contacto = DatosDinamicos.contactoValido();
        OnStage.theActorCalled("Edwin").wasAbleTo(AbrirContactList.enElHome());
    }

    @Cuando("crea una cuenta nueva desde la interfaz")
    public void creaUnaCuentaNuevaDesdeLaInterfaz() {
        Actor edwin = OnStage.theActorInTheSpotlight();
        edwin.attemptsTo(RegistrarUsuarioContactList.con(usuario));
    }

    @Cuando("cierra sesion en la aplicacion")
    public void cierraSesionEnLaAplicacion() {
        Actor edwin = OnStage.theActorInTheSpotlight();
        edwin.attemptsTo(CerrarSesionContactList.ahora());
    }

    @Cuando("inicia sesion desde la interfaz con sus credenciales")
    public void iniciaSesionDesdeLaInterfazConSusCredenciales() {
        Actor edwin = OnStage.theActorInTheSpotlight();
        edwin.attemptsTo(IniciarSesionContactList.con(usuario));
    }

    @Cuando("registra un contacto desde la interfaz")
    public void registraUnContactoDesdeLaInterfaz() {
        Actor edwin = OnStage.theActorInTheSpotlight();
        edwin.attemptsTo(CrearContactoUi.con(contacto));
    }

    @Entonces("debe ver el contacto creado en la lista")
    public void debeVerElContactoCreadoEnLaLista() {
        Actor edwin = OnStage.theActorInTheSpotlight();
        edwin.should(GivenWhenThen.seeThat("el contacto creado aparece en la tabla",
                ContactoVisible.enLaLista(contacto), is(true)));
    }
}
