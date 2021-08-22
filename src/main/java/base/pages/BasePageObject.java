package base.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;


public class BasePageObject {

    protected WebDriver driver;
    protected Logger log;

    public BasePageObject(WebDriver driver, Logger log) {
        this.driver = driver;
        this.log = log;
    }

    /**
     * Open page with given URL
     */
    public void openUrl(String url) {
        driver.get(url);
    }

    /**
     * Get URL of current page from browser
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Get title of current page
     */
    public String getCurrentPageTitle() {
        return driver.getTitle();
    }

    /**
     * Get current page source
     */
    public String getCurrentPageSource() {
        return driver.getPageSource();
    }

    /**
     * Find element using given locator when it's visible
     */
    protected WebElement find(By locator) {
        try {
            return driver.findElement(locator);
        } catch (NoSuchElementException e) {
            log.error("Element not found: [" + locator + "]");
            log.error(e.toString());
            return null;
        }
    }

    /**
     * Type given text into element with given locator
     */
    public void type(String text, By locator) {
        waitForVisibilityOf(locator, 6);
        find(locator).sendKeys(text);
    }

    /**
     * Find element using given locator when it's visible
     */
    protected List<WebElement> findListOfElements(By locator) {
        waitForVisibilityOf(locator);
        return driver.findElements(locator);
    }

    /**
     * Find list of elements using given locator and JavascriptExecutor
     */
    protected List<WebElement> jsFindListOfElements(String locator) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        List<WebElement> listOfElements = (List<WebElement>) js.executeScript(
                "var results = new Array();"
                        + "var element = document.evaluate(\"" + locator + "\", document, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null);"
                        + "for (var i=0; i<element.snapshotLength; i++)"
                        + "{"
                        + "results.push(element.snapshotItem(i));"
                        + "}"
                        + "return results;", "");
        return listOfElements;
    }


    /**
     * Click on element using given locator when it's visible
     */
    protected void click(By locator) {
        waitForVisibilityOf(locator, 5);
        waitForElementToBeClickable(locator);
        find(locator).click();
    }


    /**
     * Double Click on element using given locator and Actions
     */
    protected void doubleClick(By locator) {
        Actions action = new Actions(driver);
        action.moveToElement(find(locator)).doubleClick().build().perform();
    }

    /**
     * Move to specific element by given locator
     */
    public void moveToElement(By locator) {
        waitForVisibilityOf(locator);
        Actions action = new Actions(driver);
        action.moveToElement(find(locator)).build().perform();
    }

    /**
     * Move to specific element by given locator and click
     */
    public void moveToElementAndClick(Object locator) {
        Actions action = new Actions(driver);
        action.moveToElement((WebElement) locator).click().build().perform();
    }

    /**
     * Move to specific element by given locator and click, using JavascriptExecutor
     */
    public void jsClick(By locator) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        waitForVisibilityOf(locator);
        executor.executeScript("arguments[0].click();", find(locator));
    }

    /**
     * Scroll to element by given locator, using JavascriptExecutor
     */
    public void jsScroll(By locator) {
        int elementLocation = find(locator).getLocation().getY();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0," + elementLocation + ")");
    }


    /**
     * Navigate back in browser
     */
    public void back() {
        driver.navigate().back();
    }

    /**
     * Check if element is displayed using given locator
     */
    public boolean isElementDisplayed(By locator) {
        try {
            return find(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Check if element is present using given locator
     */
    public Integer isElementPresent(By locator) {
        return driver.findElements(locator).size();
    }

    /**
     * Find element in List of elements by getText and click on element
     */
    public void findElementInListByText(String value, By locator) {
        waitForVisibilityOf(locator);
        List<WebElement> listOfElements = findListOfElements(locator);
        if (listOfElements != null && !listOfElements.isEmpty()) {
            for (WebElement element : listOfElements) {
                if (value.toLowerCase(Locale.ROOT).equals(element.getText().toLowerCase(Locale.ROOT))) {
                    element.click();
                    break;
                }
            }
        } else {
            log.info("List is null or empty! Elements not found by locator: [" + locator + "]");
        }
    }

    /**
     * Find element in List of elements by getAttribute and click on element
     */
    public void findElementInListByAttribute(String attribute, String value, By locator) {
        waitForVisibilityOf(locator);
        List<WebElement> listOfElements = findListOfElements(locator);
        if (listOfElements != null && !listOfElements.isEmpty()) {
            for (WebElement element : listOfElements) {
                if (value.toLowerCase(Locale.ROOT).equals(element.getAttribute(attribute).toLowerCase(Locale.ROOT))) {
                    element.click();
                    break;
                }
            }
        } else {
            log.info("List is null or empty! Elements not found by locator: [" + locator + "]");
        }
    }

    /**
     * Check if list of elements are present and displayed
     */
    public boolean checkIfElementsArePresentAndDisplayed(By locator) {
        boolean isDisplayed = true;
        List<WebElement> listOfElements = findListOfElements(locator);
        if (listOfElements != null && !listOfElements.isEmpty()) {
            for (WebElement element : listOfElements) {
                if (element.getSize() == null && !element.isDisplayed()) {
                    isDisplayed = false;
                }
            }
        }
        return isDisplayed;
    }

    /**
     * Switch to child window
     */
    public void switchToChildTab() {
        for (String child : driver.getWindowHandles()) {
            try {
                driver.switchTo().window(child);
            } catch (NoSuchWindowException e) {
                log.error("Window is not found");
                log.error(e.toString());
            }
        }
    }

    /**
     * Switch to frame by given locator
     */
    public void switchToFrame(By locator) {
        try {
            driver.switchTo().frame(find(locator));
        } catch (NoSuchFrameException e) {
            log.error("Unable to locate frame with locator: [" + locator + "]");
            log.error(e.toString());
        } catch (Exception e) {
            log.error("Unable to navigate to frame with locator: [" + locator + "]");
            log.error(e.toString());
        }
    }

    /**
     * Generate Local Date Time in specific format
     */
    public String generateLocalDateTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String formatLocalDateTime = localDateTime.format(dateTimeFormatter);
        return formatLocalDateTime;
    }

    /**
     * Wait for specific ExpectedCondition for the given amount of time in seconds
     */
    private void waitFor(ExpectedCondition condition, Integer timeOutInSeconds) {
        timeOutInSeconds = timeOutInSeconds != null ? timeOutInSeconds : 60;
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(condition);
    }


    /**
     * Wait for given number of seconds for element with given locator to be visible
     */
    protected void waitForVisibilityOf(By locator, Integer... timeOutInSeconds) {
        int attempts = 0;
        while (attempts < 2) {
            try {
                waitFor(ExpectedConditions.visibilityOfElementLocated(locator),
                        timeOutInSeconds.length > 0 ? timeOutInSeconds[0] : null);
                break;
            } catch (StaleElementReferenceException e) {
                log.error(e.toString());
            }
            attempts++;
        }
    }

    /**
     * Wait number of seconds for staleness of element with given locator
     */
    protected void waitForStalenessOf(By locator, Integer... timeOutInSeconds) {
        int attempts = 0;
        while (attempts < 2) {
            try {
                waitFor(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(find(locator))),
                        timeOutInSeconds.length > 0 ? timeOutInSeconds[0] : null);
                break;
            } catch (StaleElementReferenceException e) {
                log.error(e.toString());
            }
            attempts++;
        }
    }

    /**
     * Wait for element with given locator to be clickable
     */
    protected void waitForElementToBeClickable(By locator, Integer... timeOutInSeconds) {
        int attempts = 0;
        while (attempts < 2) {
            try {
                waitFor(ExpectedConditions.elementToBeClickable(locator),
                        timeOutInSeconds.length > 0 ? timeOutInSeconds[0] : null);
                break;
            } catch (StaleElementReferenceException e) {
                log.error(e.toString());
            }
            attempts++;
        }
    }

    /**
     * Wait for element with given locator and text to be present in element
     */
    protected void waitForTextToBePresentInElement(By locator, String text, Integer... timeOutInSeconds) {
        int attempts = 0;
        while (attempts < 2) {
            try {
                waitFor(ExpectedConditions.textToBePresentInElement(find(locator), text),
                        timeOutInSeconds.length > 0 ? timeOutInSeconds[0] : null);
                break;
            } catch (StaleElementReferenceException e) {
                log.error(e.toString());
            }
            attempts++;
        }
    }

}
