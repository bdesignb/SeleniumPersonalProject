package pages.amazon;

import base.pages.BasePageObject;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Locale;

public class AmazonHomePage extends BasePageObject {

    private By signInLinkLocator = By.id("nav-link-accountList-nav-line-1");
    private By createAmazonAccountLocator = By.id("createAccountSubmit");
    private By searchInputLocator = By.id("twotabsearchtextbox");
    private By searchButtonLocator = By.id("nav-search-submit-button");
    private By searchResultLocator = By.xpath("//span[starts-with(@class, 'a-size-medium')]");
    private By allListLocator = By.id("nav-hamburger-menu");
    private By sideMenuLocator = By.xpath("//ul[contains(@class, 'hmenu hmenu-visible')]/li/a");

    public AmazonHomePage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    /**
     * Open Amazon page url
     */
    public void openPage(String pageUrl) {
        log.info("Opening page: " + pageUrl);
        openUrl(pageUrl);
        log.info("Page opened!");
    }

    /**
     * Click on 'Sign in' link
     */
    public void clickOnSignInLink() {
        log.info("Clicking on 'Sign In' link!");
        click(signInLinkLocator);
    }

    /**
     * Click on 'Create your Amazon account' link
     */
    public RegistrationPage clickOnCreateAmazonAccountLink() {
        log.info("Clicking on 'Create your Amazon Account' link!");
        click(createAmazonAccountLocator);
        return new RegistrationPage(driver, log);
    }

    /**
     * Search for text
     */
    public void searchText(String searchBy) {
        log.info("Searching for the text: [" + searchBy + "]");
        type(searchBy, searchInputLocator);
        log.info("Clicking search button!");
        click(searchButtonLocator);
    }

    /**
     * Get first result from list of search results
     */
    public String getFirstResult() {
        waitForVisibilityOf(searchResultLocator);
        String firstResult = "";

        List<WebElement> listOfSearchResults = findListOfElements(searchResultLocator);
        log.info("Checking if the first result contains word that we are searching for!");
        if (listOfSearchResults != null && !listOfSearchResults.isEmpty()) {
            firstResult = listOfSearchResults.get(0).getText().toLowerCase(Locale.ROOT);
        } else {
            log.info("List is null or empty!");
        }

        return firstResult;
    }


    /**
     * Click on 'All List' button
     */
    public void clickOnAllList() {
        click(allListLocator);
    }

    /**
     * Click on given item from menu
     */
    public AllGiftCardsPage selectItemFromMenu(String menuItem) {
        log.info("Selecting item: [" + menuItem + "] from menu.");
        findElementInListByText(menuItem, sideMenuLocator);
        return new AllGiftCardsPage(driver, log);
    }


}
