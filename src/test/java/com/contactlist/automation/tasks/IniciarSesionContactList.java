package com.contactlist.automation.tasks;

import com.contactlist.automation.models.UsuarioContactList;
import com.contactlist.automation.userinterfaces.ContactListHomePage;
import com.contactlist.automation.userinterfaces.ContactListPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class IniciarSesionContactList implements Task {

    private final UsuarioContactList usuario;

    public IniciarSesionContactList(UsuarioContactList usuario) {
        this.usuario = usuario;
    }

    public static IniciarSesionContactList con(UsuarioContactList usuario) {
        return Tasks.instrumented(IniciarSesionContactList.class, usuario);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(usuario.getEmail()).into(ContactListHomePage.EMAIL),
                Enter.theValue(usuario.getPassword()).into(ContactListHomePage.PASSWORD),
                Click.on(ContactListHomePage.SUBMIT),
                WaitUntil.the(ContactListPage.ADD_CONTACT, isVisible()).forNoMoreThan(15).seconds()
        );
    }
}
