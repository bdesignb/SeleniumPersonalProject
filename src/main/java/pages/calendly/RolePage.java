package pages.calendly;

import base.pages.BasePageObject;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RolePage extends BasePageObject {

    private By roleRadioButtonLocator = By.xpath("//label[@name='role']");
    private By finishSpanLocator = By.xpath("//span[contains(text(),'Finish')]");

    public RolePage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    /**
     * Choose Role at Work
     */
    public EventPage chooseRoleAtWork(String role) {
        waitForVisibilityOf(roleRadioButtonLocator);

        log.info("Selecting specific role!");
        findElementInListByText(role, roleRadioButtonLocator);
        log.info("Role: [" + role + "] is selected.");

        click(finishSpanLocator);
        return new EventPage(driver, log);
    }

}
