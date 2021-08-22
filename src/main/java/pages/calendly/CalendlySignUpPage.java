package pages.calendly;

import base.pages.BasePageObject;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;

public class CalendlySignUpPage extends BasePageObject {

    private By signUpButtonLocator = By.xpath("//*[contains(text(),'Sign up') or contains(text(),'Sign up free')]");
    private By emailInputLocator = By.xpath("//input[@type='email']");
    private By signUpSubmitLocator = By.xpath("//button[contains(text(), 'Sign up')]");
    private By clickHereLocator = By.xpath("//button[contains(text(),'Click here')]");
    private By nameInputLocator = By.xpath("//input[@name='name']");
    private By passwordInputLocator = By.xpath("//input[@name='password']");
    private By continueButtonLocator = By.xpath("//input[@value='Continue']");
    private By agreeCheckboxLocator = By.cssSelector(".js-tos-agreed");
    private By getStartedButtonLocator = By.xpath("//input[@value='Get Started']");


    public CalendlySignUpPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    /**
     * Open Calendly page url
     */
    public void openCalendlyUrl(String pageUrl) {
        log.info("Opening Calendly page url: " + pageUrl);
        openUrl(pageUrl);
        log.info("Page is opened");
    }

    /**
     * Click on Sign Up button
     */
    public void clickOnSignUpButton() {
        log.info("Clicking on 'Sign up' button");
        jsClick(signUpButtonLocator);
    }

    /**
     * Sign Up (Enter Email)
     */
    public void enterEmail(String email) {
        log.info("Sign up with email: [" + email + "]");

        // Perform this actions to prevent Exceptions
        waitForVisibilityOf(emailInputLocator);
        moveToElement(emailInputLocator);
        waitForElementToBeClickable(emailInputLocator);

        type(email, emailInputLocator);
        if (isElementPresent(getStartedButtonLocator) != 0) {
            click(getStartedButtonLocator);
        } else {
            moveToElement(signUpButtonLocator);
            click(signUpSubmitLocator);
        }
        click(clickHereLocator);
    }


    /**
     * Sign Up (Enter Name and Password)
     */
    public void enterNameAndPassword(String name, String password) {
        log.info("Sign up with name: [" + name + "] and password: [" + password + "]");
        type(name, nameInputLocator);
        type(password, passwordInputLocator);
        if(isElementPresent(agreeCheckboxLocator) != 0) {
            click(agreeCheckboxLocator);
        };
        click(continueButtonLocator);
    }
}

