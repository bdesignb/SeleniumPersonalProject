package pages.calendly;

import base.pages.BasePageObject;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class CalendlyLoginPage extends BasePageObject {
    private By passwordInputLocator = By.xpath("//input[@name='password']");
    private By continueButtonLocator = By.xpath("//input[@value='Continue']");
    private By customCalendlyURLLocator = By.xpath("//input[@name='slug']");
    private By continueLocator = By.xpath("//*[contains(text(),'Continue')]");
    private By continueWithoutCalendar = By.xpath("//div[@class='clickable']");


    public CalendlyLoginPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    /**
     * Get custom Calendly URL
     */
    public String getCustomCalendlyURl() {
        waitForVisibilityOf(customCalendlyURLLocator);
        return find(customCalendlyURLLocator).getAttribute("value");
    }

    /**
     * Login into Calendly
     */
    public void logIn(String password) {
        log.info("Login into Calendly!");
        type(password, passwordInputLocator);
        click(continueButtonLocator);
        log.info("Logged in with password: [" + password + "].");
    }

    /**
     * Continue without Calendar
     */
    public AvailabilityPage continueWithoutCalendar() {
        click(continueLocator);
        click(continueWithoutCalendar);
        return new AvailabilityPage(driver, log);
    }

}
