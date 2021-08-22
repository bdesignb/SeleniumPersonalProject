package pages.botsify;

import base.pages.BasePageObject;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BotsifyHomePage extends BasePageObject {

    private By chatBotLocator = By.id("wpic-launcher-child");

    public BotsifyHomePage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    /**
     * Open Botsify page url
     */
    public void openPage(String pageUrl) {
        log.info("Opening Botsify page URL: " + pageUrl);
        openUrl(pageUrl);
        log.info("Page opened!");
    }

    /**
     * Click on start bot, for starting a conversation
     */
    public ConversationPage clickOnChatBot() {
        log.info("Clicking on chat bot!");
        click(chatBotLocator);
        return new ConversationPage(driver, log);
    }
}
