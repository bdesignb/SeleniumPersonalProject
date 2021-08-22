package pages.amazon;

import base.pages.BasePageObject;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class JustBecauseGiftCardPage extends BasePageObject {

    private By cardLocator = By.xpath("//div[contains(@id, 'anonCarousel1')]/ol/li/div/a");
    private By cardSizeLocator = By.xpath("//div[contains(@id, 'anonCarousel1')]/ol/li");
    private By carouselRightButtonLocator = By.id("a-autoid-4");
    private By priceLocator = By.id("priceblock_ourprice");

    public JustBecauseGiftCardPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public void findCardInMailDeliveryList(String cardName) {
        boolean cardFound = false;
        int count = 0;
        int totalNumberOfCards = Integer.parseInt(find(cardSizeLocator).getAttribute("aria-setsize"));

        while (!cardFound && (totalNumberOfCards > count)) {
            List<WebElement> listOfCards = findListOfElements(cardLocator);
            if (listOfCards != null && !listOfCards.isEmpty()) {
                for (WebElement card : listOfCards) {
                    count++;
                    if (card.getText().equals(cardName)) {
                        log.info("Card with name: [" + cardName + "] is found.");
                        cardFound = true;
                        card.click();
                        log.info("Searching for card price!");
                        String price = find(priceLocator).getText();
                        log.info("Price for given card: [" + price + "]");
                        break;
                    }
                }
                if (!cardFound && (totalNumberOfCards > count)) {
                    click(carouselRightButtonLocator);
                    waitForStalenessOf(cardLocator);
                } else if (!cardFound && (totalNumberOfCards == count)) {
                    log.info("Card with name: [" + cardName + "] is not found.");
                }
            }
        }
    }
}



