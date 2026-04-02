package com.contactlist.automation.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class ContactListSignupPage {

    public static final Target FIRST_NAME = Target.the("campo first name")
            .located(By.id("firstName"));

    public static final Target LAST_NAME = Target.the("campo last name")
            .located(By.id("lastName"));

    public static final Target EMAIL = Target.the("campo email de registro")
            .located(By.id("email"));

    public static final Target PASSWORD = Target.the("campo password de registro")
            .located(By.id("password"));

    public static final Target SUBMIT = Target.the("boton enviar registro")
            .located(By.id("submit"));

    private ContactListSignupPage() {
    }
}
