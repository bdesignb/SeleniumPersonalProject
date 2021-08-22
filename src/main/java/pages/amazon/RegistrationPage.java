package pages.amazon;

import base.pages.BasePageObject;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage extends BasePageObject {

    private By registrationElementLocator = By.xpath("//input[starts-with(@class, 'a-input-text')]");

    public RegistrationPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    /** Validate all elements of the registration page, that are present and displayed */
    public boolean validateElementsOnRegistrationPage() {
        log.info("Checking if all elements of the registration page are present and displayed.");
        return checkIfElementsArePresentAndDisplayed(registrationElementLocator);
    }


}
