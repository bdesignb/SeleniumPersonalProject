package pages.calendly;

import base.pages.BasePageObject;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EventPage extends BasePageObject {

    private By accountLocator = By.xpath("//div[contains(text(),'Account')]");

    public EventPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    /**
     * Get Event Page URL
     */
    public String getEventPageURL() {
        waitForVisibilityOf(accountLocator);
        return getCurrentUrl();
    }

}
