package pages.calendly;

import base.pages.BasePageObject;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Locale;

public class AvailabilityPage extends BasePageObject {

    private By checkboxDayLocator = By.xpath("//input[@type='checkbox']");
    private By timeFromButtonLocator = By.id("time_from_chzn");
    private By timeFromLocator = By.xpath("//*[contains(@id, 'time_from_chzn')]");
    private By timeToButtonLocator = By.id("time_to_chzn");
    private By timeToLocator = By.xpath("//*[contains(@id, 'time_to_chzn')]");
    private By continueSpanLocator = By.xpath("//span[contains(text(),'Continue')]");

    public AvailabilityPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    /**
     * Set available days
     */
    public void setAvailableDays(String day1, String day2, String day3) {
        waitForVisibilityOf(continueSpanLocator);
        waitForElementToBeClickable(checkboxDayLocator);
        List<WebElement> daysList = findListOfElements(checkboxDayLocator);

        if (daysList != null && !daysList.isEmpty()) {
            for (WebElement day : daysList) {
                if (day.getAttribute("value").toLowerCase(Locale.ROOT).equals(day1.toLowerCase(Locale.ROOT)) ||
                        day.getAttribute("value").toLowerCase(Locale.ROOT).equals(day2.toLowerCase(Locale.ROOT)) ||
                        day.getAttribute("value").toLowerCase(Locale.ROOT).equals(day3.toLowerCase(Locale.ROOT))) {
                    if (day.getAttribute("checked") == null) {
                        moveToElementAndClick(day);
                    }
                } else {
                    if (day.getAttribute("checked") != null) {
                        moveToElementAndClick(day);
                    }
                }
            }
            log.info("Days [" + day1 + "/" + day2 + "/" + day3 + "] are selected for available days.");

        } else {
            log.info("Days list is empty or null!");
        }
    }


    /**
     * Set available hours [Time from] - [Time to]
     */
    public RolePage setAvailableHours(String timeFrom, String timeTo) {
        log.info("Selecting available hours!");
        click(timeFromButtonLocator);
        findElementInListByText(timeFrom, timeFromLocator);
        click(timeToButtonLocator);
        findElementInListByText(timeTo, timeToLocator);

        log.info("Available hours are set from: [" + timeFrom + "]" + " to: [" + timeTo + "]");
        click(continueSpanLocator);
        return new RolePage(driver, log);
    }
}