package com.contactlist.automation.questions;

import com.contactlist.automation.models.Contacto;
import com.contactlist.automation.userinterfaces.ContactListPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ContactoVisible implements Question<Boolean> {

    private final Contacto contacto;

    public ContactoVisible(Contacto contacto) {
        this.contacto = contacto;
    }

    public static ContactoVisible enLaLista(Contacto contacto) {
        return new ContactoVisible(contacto);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        return ContactListPage.CONTACT_BY_NAME.of(contacto.getFullName()).resolveFor(actor).isVisible();
    }
}
