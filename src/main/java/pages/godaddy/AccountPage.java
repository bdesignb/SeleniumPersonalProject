package pages.godaddy;

import base.pages.BasePageObject;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage extends BasePageObject {

    private By accountNameLocator = By.cssSelector(".customer-name-span");


    public AccountPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    /**
     * Get Account Name
     */
    public String getAccountNameText() {
        return find(accountNameLocator).getText();
    }

    /**
     * Waiting for account with expected Account Name to be present on page
     */
    public void waitForAccountToBePresent(String expectedAccountName, Integer timeOutInSeconds) {
        waitForVisibilityOf(accountNameLocator);
        log.info("Waiting for account with expected Account Name: " + expectedAccountName + " to be present.");
        waitForTextToBePresentInElement(accountNameLocator, expectedAccountName);
    }


}
