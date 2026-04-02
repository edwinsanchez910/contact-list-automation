package com.contactlist.automation.tasks;

import com.contactlist.automation.models.UsuarioContactList;
import com.contactlist.automation.userinterfaces.ContactListPage;
import com.contactlist.automation.userinterfaces.ContactListHomePage;
import com.contactlist.automation.userinterfaces.ContactListSignupPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class RegistrarUsuarioContactList implements Task {

    private final UsuarioContactList usuario;

    public RegistrarUsuarioContactList(UsuarioContactList usuario) {
        this.usuario = usuario;
    }

    public static RegistrarUsuarioContactList con(UsuarioContactList usuario) {
        return Tasks.instrumented(RegistrarUsuarioContactList.class, usuario);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(ContactListHomePage.SIGNUP),
                Enter.theValue(usuario.getFirstName()).into(ContactListSignupPage.FIRST_NAME),
                Enter.theValue(usuario.getLastName()).into(ContactListSignupPage.LAST_NAME),
                Enter.theValue(usuario.getEmail()).into(ContactListSignupPage.EMAIL),
                Enter.theValue(usuario.getPassword()).into(ContactListSignupPage.PASSWORD),
                Click.on(ContactListSignupPage.SUBMIT),
                WaitUntil.the(ContactListPage.ADD_CONTACT, isVisible()).forNoMoreThan(15).seconds()
        );
    }
}
