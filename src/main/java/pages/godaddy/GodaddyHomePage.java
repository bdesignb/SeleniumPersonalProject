package pages.godaddy;

import base.pages.BasePageObject;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GodaddyHomePage extends BasePageObject {

    private By signInLinkLocator = By.xpath("//span[contains(text(),'Sign In')]");
    private By createAnAccountLinkLocator = By.linkText("Create an Account");
    private By signUpEmailLocator = By.id("sign-up-email");

    public GodaddyHomePage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    /**
     * Open GoDaddy page url
     */
    public void openPage(String pageUrl) {
        log.info("Opening page: " + pageUrl);
        openUrl(pageUrl);
        log.info("Page opened!");
    }

    /**
     * Click on Sign In link
     */
    public void clickSignInLink() {
        log.info("Clicking on 'Sign In' link");
        click(signInLinkLocator);
    }

    /**
     * Open RegistrationPage by clicking on 'Create an Account' link
     */
    public RegistrationPage clickCreateAnAccountLink() {
        log.info("Clicking on 'Create an Account' link and Sign Up");
        click(createAnAccountLinkLocator);
        click(signUpEmailLocator);
        return new RegistrationPage(driver, log);
    }

}
