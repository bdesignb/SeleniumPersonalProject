package pages.amazon;

import base.pages.BasePageObject;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AllGiftCardsPage extends BasePageObject {

    private By seeAllLocator = By.xpath("//a[contains(@aria-label, 'Find Gift Cards for All Occasions')]");
    private By occasionLocator = By.xpath("//div[contains(@class, 'bxc-grid__image')]//a");

    public AllGiftCardsPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    /** Find 'See All' in section 'Shop By Occasion' and click on it */
    public void shopByOccasion(String occasion) {
        log.info("Clicking on 'See All' button!");
        findElementInListByAttribute("aria-label", occasion, occasionLocator);
    }

    /** Find given occasion in list of occasions and click on it */
    public JustBecauseGiftCardPage chooseOccasion(String occasion) {
        log.info("Choose occasion: [" + occasion + "]");
        findElementInListByAttribute("aria-label", occasion, occasionLocator);
        return new JustBecauseGiftCardPage(driver, log);
    }

}
