package com.contactlist.automation.tasks;

import com.contactlist.automation.userinterfaces.ContactListHomePage;
import com.contactlist.automation.userinterfaces.ContactListPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class CerrarSesionContactList implements Task {

    public static CerrarSesionContactList ahora() {
        return Tasks.instrumented(CerrarSesionContactList.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(ContactListPage.LOGOUT),
                WaitUntil.the(ContactListHomePage.SUBMIT, isVisible()).forNoMoreThan(10).seconds()
        );
    }
}
