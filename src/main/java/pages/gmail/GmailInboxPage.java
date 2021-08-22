package pages.gmail;

import base.pages.BasePageObject;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.calendly.CalendlyLoginPage;

public class GmailInboxPage extends BasePageObject {

    private By emailSubjectLocator = By.xpath("//span[@class='bog']/span");
    private By confirmMyEmailLocator = By.linkText("Confirm My Email");
    private By profileLogoLocator = By.xpath("//img[@class='gb_Ca gbii']");
    private By emailLocator = By.xpath("//div[@class='gb_nb']");

    public GmailInboxPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    /**
     * Wait for Gmail account to be loaded and get email address
     */
    public String getEmailAddress() {
        click(profileLogoLocator);
        String actualEmail = find(emailLocator).getText();
        log.info("Successfully logged in into gmail account with email: [" + actualEmail + "]");
        click(profileLogoLocator);
        return actualEmail;
    }

    /**
     * Find email with specific subject and click on 'Confirm my email'
     */
    public void findEmailWithSpecificSubject(String emailSubject) {
        log.info("Clicking on email with subject: " + emailSubject);
        findElementInListByText(emailSubject, emailSubjectLocator);
    }

    /**
     * Find email with specific subject and click on 'Confirm my email'
     */
    public CalendlyLoginPage confirmEmail(String email) {
        jsScroll(By.xpath("//span[@email='" + email + "']"));
        log.info("Clicking on 'Confirm My Email' button");
        click(confirmMyEmailLocator);
        return new CalendlyLoginPage(driver, log);
    }

}
