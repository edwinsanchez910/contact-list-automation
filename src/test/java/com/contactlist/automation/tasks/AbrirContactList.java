package com.contactlist.automation.tasks;

import com.contactlist.automation.userinterfaces.ContactListHomePage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Open;

public class AbrirContactList implements Task {

    public static AbrirContactList enElHome() {
        return Tasks.instrumented(AbrirContactList.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Open.browserOn().the(ContactListHomePage.class));
    }
}
