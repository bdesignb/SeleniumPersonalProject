package pages.gmail;

import base.pages.BasePageObject;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GmailLoginPage extends BasePageObject {

    private By emailInputLocator = By.id("identifierId");
    private By nextEmailButtonLocator = By.id("identifierNext");
    private By passwordInputLocator = By.xpath("//input[@name='password']");
    private By nextPasswordButtonLocator = By.id("passwordNext");

    public GmailLoginPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    /**
     * Open Gmail login page url
     */
    public void openGmailLoginPage(String pageUrl) {
        log.info("Opening Gmail login page: " + pageUrl);
        openUrl(pageUrl);
        log.info("Gmail Login page opened!");
    }

    /**
     * Login into Gmail account
     */
    public GmailInboxPage logIn(String email, String password) {
        log.info("Login in with email: [" + email + "] and password [" + password + "]");
        type(email, emailInputLocator);
        click(nextEmailButtonLocator);
        type(password, passwordInputLocator);
        click(nextPasswordButtonLocator);
        return new GmailInboxPage(driver, log);
    }

}
