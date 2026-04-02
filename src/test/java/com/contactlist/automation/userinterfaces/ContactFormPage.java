package com.contactlist.automation.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class ContactFormPage {

    public static final Target FIRST_NAME = Target.the("campo first name del contacto")
            .located(By.id("firstName"));

    public static final Target LAST_NAME = Target.the("campo last name del contacto")
            .located(By.id("lastName"));

    public static final Target BIRTHDATE = Target.the("campo birthdate")
            .located(By.id("birthdate"));

    public static final Target EMAIL = Target.the("campo email del contacto")
            .located(By.id("email"));

    public static final Target PHONE = Target.the("campo phone")
            .located(By.id("phone"));

    public static final Target STREET1 = Target.the("campo street1")
            .located(By.id("street1"));

    public static final Target STREET2 = Target.the("campo street2")
            .located(By.id("street2"));

    public static final Target CITY = Target.the("campo city")
            .located(By.id("city"));

    public static final Target STATE_PROVINCE = Target.the("campo state province")
            .located(By.id("stateProvince"));

    public static final Target POSTAL_CODE = Target.the("campo postal code")
            .located(By.id("postalCode"));

    public static final Target COUNTRY = Target.the("campo country")
            .located(By.id("country"));

    public static final Target SUBMIT = Target.the("boton submit contacto")
            .located(By.id("submit"));

    private ContactFormPage() {
    }
}
