package pages.godaddy;

import base.pages.BasePageObject;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage extends BasePageObject {

    private By emailLocator = By.id("email");
    private By usernameLocator = By.id("username");
    private By passwordLocator = By.id("new-password");
    private By inputLocator = By.className("ff-input");
    private By agreeLocator = By.xpath("//span[contains(text(),'Agree')]");
    private By createAccountButtonLocator = By.xpath("//span[contains(text(),'Create Account')]");


    public RegistrationPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    /**
     * Validate elements of registration page
     */
    public boolean validateElementsOfRegistrationPage() {
        return checkIfElementsArePresentAndDisplayed(inputLocator);
    }

    /**
     * Create account with given email/username/password
     */
    public AccountPage createAccount(String email, String password) {
        log.info("Creating an account with email: [" + email + "] and password [" + password + "]");
        if (isElementPresent(emailLocator) != 0) {
            type(email, emailLocator);
        } else {
            type(email, usernameLocator);
        }
        type(password, passwordLocator);
        click(agreeLocator);
        doubleClick(createAccountButtonLocator);
        return new AccountPage(driver, log);
    }


}
