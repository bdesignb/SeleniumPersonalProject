package medium;

import base.base.PropertyReader;
import base.base.TestUtilities;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.amazon.AmazonHomePage;
import pages.amazon.RegistrationPage;

import java.util.Locale;

public class MediumTests extends TestUtilities {

    @Test
    public void amazonTestsTC1() throws Exception {

        // Create Property Reader object to read data
        PropertyReader prop = new PropertyReader();

        // Open Amazon Home Page
        AmazonHomePage amazonHomePage = new AmazonHomePage(driver, log);
        amazonHomePage.openPage(prop.getProperty("amazonPageUrl"));

        // Get current page title and print it
        String homePageTitle = amazonHomePage.getCurrentPageTitle();
        log.info("Current page title is: [" + homePageTitle + "]");

        // Click on any 'Sign in' link
        amazonHomePage.clickOnSignInLink();

        // Navigate back to the home page.
        amazonHomePage.back();

        // Verify that current page title matches with home page title
        String currentPageTitle = amazonHomePage.getCurrentPageTitle();
        Assert.assertEquals(currentPageTitle, homePageTitle,
                "Current page title is not equal to Amazon home page title! " +
                        "\nExpected page title: " + homePageTitle +
                        "\nActual page title : " + currentPageTitle);
    }

    @Test
    public void amazonTestsTC2() throws Exception {

        // Create Property Reader object to read data
        PropertyReader prop = new PropertyReader();

        // Open Amazon Home Page
        AmazonHomePage amazonHomePage = new AmazonHomePage(driver, log);
        amazonHomePage.openPage(prop.getProperty("amazonPageUrl"));

        // Click on any 'Sign in' link on the page
        amazonHomePage.clickOnSignInLink();

        // Click on any 'Create your Amazon account' link and navigate to Registration Page
        RegistrationPage registrationPage = amazonHomePage.clickOnCreateAmazonAccountLink();

        // Validate all elements of the registration page
        Assert.assertEquals(true, registrationPage.validateElementsOnRegistrationPage(),
                "Validation failed! All elements of registration page are not displayed");
    }

    @Test
    public void amazonTestsTC3() throws Exception {

        // Create Property Reader object to read data
        PropertyReader prop = new PropertyReader();

        SoftAssert softAssert = new SoftAssert();

        // Open Amazon Home Page
        AmazonHomePage amazonHomePage = new AmazonHomePage(driver, log);
        amazonHomePage.openPage(prop.getProperty("amazonPageUrl"));

        // Input valid text into search bar
        String searchValidText = prop.getProperty("searchValidText").toLowerCase(Locale.ROOT);
        amazonHomePage.searchText(searchValidText);

        // Check if the first result contains word that you searching for
        String firstResult = amazonHomePage.getFirstResult();
        softAssert.assertEquals(true, firstResult.contains(searchValidText),
                "First result does not contain word that you are searching for! "
                        + "\nSearch Valid Text: " + searchValidText
                        + "\nFirst result: " + firstResult);

        softAssert.assertAll();
    }

    @Test
    public void amazonTestsTC4() throws Exception {

        // Create Property Reader object to read data
        PropertyReader prop = new PropertyReader();

        SoftAssert softAssert = new SoftAssert();

        // Open Amazon Home Page
        AmazonHomePage amazonHomePage = new AmazonHomePage(driver, log);
        amazonHomePage.openPage(prop.getProperty("amazonPageUrl"));

        // Input valid text into search bar
        String searchInvalidText = prop.getProperty("searchInvalidText").toLowerCase(Locale.ROOT);
        amazonHomePage.searchText(searchInvalidText);

        // Check if the first result does not contains word that you searching for
        String firstResult = amazonHomePage.getFirstResult();
        softAssert.assertNotEquals(true, firstResult.contains(searchInvalidText),
                "First result contains invalid word that you are searching for! "
                        + "\nSearch invalid text: " + searchInvalidText
                        + "\nFirst result: " + firstResult);
        softAssert.assertAll();
    }
}
