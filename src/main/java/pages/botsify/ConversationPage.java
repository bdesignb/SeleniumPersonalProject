package pages.botsify;


import base.pages.BasePageObject;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ConversationPage extends BasePageObject {

    private By nameLocator = By.id("wpic-username");
    private By emailLocator = By.id("wpic-email");
    private By startConversationButtonLocator = By.id("wpic-login");
    private By frameLocator = By.id("botsify-chat-widget-frame");
    private By buttonLocator = By.xpath("//button[contains(text(),'Features') or contains(text(),'No')]");
    private String featuresTextLocator = "//p[contains(text(), 'Features')]/following::div/div/div/p[@class='chat-text']";
    private By noTextLocator = By.className("botsify-text-sec");
    private By pickASlotLocator = By.linkText("Pick a slot");
    private By okTextLocator = By.xpath("//div[@class='home_heading']/h2");
    private By mainManuLocator = By.id("main-menu");
    private By deleteConversationLocator = By.xpath("//a[contains(text(),'Delete Conversation') or contains(text(),'Yes, delete it!')]");

    public ConversationPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    /**
     * Click on 'Features' button
     */
    public void clickOnFeaturesButton() {
        log.info("Clicking on 'Features' button!");
        click(buttonLocator);
    }

    /**
     * Switch to frame with given locator
     */
    public void switchToFrame() {
        log.info("Switching to frame: [" + frameLocator + "]");
        switchToFrame(frameLocator);
    }

    /**
     * Starting conversation with given name + email
     */
    public void startConversation(String name, String email) {
        log.info("Starting conversation with name: [" + name + "] and email: [" + email + "]");
        type(name, nameLocator);
        type(email, emailLocator);
        log.info("Clicking on 'Start Conversation' button!");
        click(startConversationButtonLocator);
    }

    /**
     * Get response text after click on Features button by given index
     */
    public String getFeaturesResponse(int i) {
        waitForVisibilityOf(buttonLocator);
        String featureText = "";
        List<WebElement> listOfFeatureText = jsFindListOfElements(featuresTextLocator);
        if (listOfFeatureText != null && !listOfFeatureText.isEmpty()) {
            featureText = listOfFeatureText.get(i).getText();
        }
        return featureText;
    }

    /**
     * Click on 'No' button
     */
    public void clickOnNoButton() {
        log.info("Clicking on 'No' button!");
        click(buttonLocator);
    }

    /**
     * Get response text after click on No button
     */
    public String getNoResponse() {
        waitForVisibilityOf(pickASlotLocator);
        return find(noTextLocator).getText();
    }

    /**
     * Click on 'Pick a Slot' button
     */
    public void clickOnPickASlot() {
        log.info("Clicking on 'Pick a Slot' button!");
        click(pickASlotLocator);
    }

    /**
     * Click on the “main menu” in chat-bot
     */
    public void clickOnMainMenuChatBot() {
        log.info("Clicking on 'main menu' button!");
        click(mainManuLocator);
    }

    /**
     * Click on 'Delete conversation' button
     */
    public void deleteConversation() {
        log.info("Clicking on 'Delete conversation' button!");
        click(deleteConversationLocator);
    }

    /**
     * Click on 'Delete conversation' button
     */
    public void confirmDelete() {
        log.info("Clicking on 'Confirm delete' button!");
        click(deleteConversationLocator);
    }

    /**
     * Get response text after click on Ok button
     */
    public String getOkResponse() {
        waitForVisibilityOf(okTextLocator);
        return find(okTextLocator).getText();
    }
}

