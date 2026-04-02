package com.contactlist.automation.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class ContactListPage {

    public static final Target ADD_CONTACT = Target.the("boton add a new contact")
            .located(By.id("add-contact"));

    public static final Target LOGOUT = Target.the("boton logout")
            .located(By.id("logout"));

    public static final Target CONTACT_TABLE = Target.the("tabla de contactos")
            .located(By.id("myTable"));

    public static final Target CONTACT_BY_NAME = Target.the("contacto con nombre {0}")
            .locatedBy("//table[@id='myTable']//tr[contains(.,'{0}')]");

    private ContactListPage() {
    }
}
