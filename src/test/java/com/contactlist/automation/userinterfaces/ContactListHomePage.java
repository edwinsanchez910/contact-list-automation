package com.contactlist.automation.userinterfaces;

import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

@DefaultUrl("https://thinking-tester-contact-list.herokuapp.com/")
public class ContactListHomePage extends PageObject {

    public static final Target EMAIL = Target.the("campo email")
            .located(By.id("email"));

    public static final Target PASSWORD = Target.the("campo password")
            .located(By.id("password"));

    public static final Target SUBMIT = Target.the("boton submit")
            .located(By.id("submit"));

    public static final Target SIGNUP = Target.the("boton sign up")
            .located(By.id("signup"));
}
