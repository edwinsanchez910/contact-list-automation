package com.contactlist.automation.tasks;

import com.contactlist.automation.models.Contacto;
import com.contactlist.automation.userinterfaces.ContactFormPage;
import com.contactlist.automation.userinterfaces.ContactListPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class CrearContactoUi implements Task {

    private final Contacto contacto;

    public CrearContactoUi(Contacto contacto) {
        this.contacto = contacto;
    }

    public static CrearContactoUi con(Contacto contacto) {
        return Tasks.instrumented(CrearContactoUi.class, contacto);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(ContactListPage.ADD_CONTACT),
                Enter.theValue(contacto.getFirstName()).into(ContactFormPage.FIRST_NAME),
                Enter.theValue(contacto.getLastName()).into(ContactFormPage.LAST_NAME),
                Enter.theValue(contacto.getBirthdate()).into(ContactFormPage.BIRTHDATE),
                Enter.theValue(contacto.getEmail()).into(ContactFormPage.EMAIL),
                Enter.theValue(contacto.getPhone()).into(ContactFormPage.PHONE),
                Enter.theValue(contacto.getStreet1()).into(ContactFormPage.STREET1),
                Enter.theValue(contacto.getStreet2()).into(ContactFormPage.STREET2),
                Enter.theValue(contacto.getCity()).into(ContactFormPage.CITY),
                Enter.theValue(contacto.getStateProvince()).into(ContactFormPage.STATE_PROVINCE),
                Enter.theValue(contacto.getPostalCode()).into(ContactFormPage.POSTAL_CODE),
                Enter.theValue(contacto.getCountry()).into(ContactFormPage.COUNTRY),
                Click.on(ContactFormPage.SUBMIT),
                WaitUntil.the(ContactListPage.CONTACT_TABLE, isVisible()).forNoMoreThan(15).seconds()
        );
    }
}
