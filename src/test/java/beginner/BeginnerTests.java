package beginner;

import base.base.PropertyReader;
import base.base.TestUtilities;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.calendly.CalendlyLoginPage;
import pages.calendly.CalendlySignUpPage;
import pages.gmail.GmailInboxPage;
import pages.gmail.GmailLoginPage;
import pages.godaddy.AccountPage;
import pages.godaddy.GodaddyHomePage;
import pages.godaddy.RegistrationPage;


public class BeginnerTests extends TestUtilities {

    private GodaddyHomePage godaddyHomePage;

    @Test
    public void godaddyTestsTC1() throws Exception {

        // Create Property Reader object to read data
        PropertyReader prop = new PropertyReader();

        // Open URL https://www.godaddy.com/
        godaddyHomePage = new GodaddyHomePage(driver, log);
        godaddyHomePage.openPage(prop.getProperty("godaddyPageUrl"));
    }

    @Test
    public void godaddyTestsTC2() throws Exception {

        // Create Property Reader object to read data
        PropertyReader prop = new PropertyReader();

        // Open Godday URL https://www.godaddy.com/
        godaddyHomePage = new GodaddyHomePage(driver, log);
        godaddyHomePage.openPage(prop.getProperty("godaddyPageUrl"));

        // Get title of the current page and print it
        String pageTitle = godaddyHomePage.getCurrentPageTitle();
        log.info("Current page title: " + pageTitle);

        // Get page source and print it
        String pageSource = godaddyHomePage.getCurrentPageSource();
        log.info("Current page source: " + pageSource);
    }

    @Test
    public void godaddyTestsTC3() throws Exception {

        // Create Property Reader object to read data
        PropertyReader prop = new PropertyReader();

        SoftAssert softAssert = new SoftAssert();

        // Open Goddady URL https://www.godaddy.com/
        godaddyHomePage = new GodaddyHomePage(driver, log);
        godaddyHomePage.openPage(prop.getProperty("godaddyPageUrl"));

        // Click on 'Sign in' Link
        godaddyHomePage.clickSignInLink();

        // Click on 'Create an Account' link and continue to Registration Page
        RegistrationPage registrationPage = godaddyHomePage.clickCreateAnAccountLink();

        // Validate elements of registration page
        softAssert.assertEquals(true, registrationPage.validateElementsOfRegistrationPage(),
                "Failed! All elements of registration page are not displayed!");

        softAssert.assertAll();
    }

    @Test
    public void godaddyTestsTC4() throws Exception {

        // Create Property Reader object to read data
        PropertyReader prop = new PropertyReader();

        SoftAssert softAssert = new SoftAssert();

        // Open  Goddady URL https://www.godaddy.com/
        godaddyHomePage = new GodaddyHomePage(driver, log);
        godaddyHomePage.openPage(prop.getProperty("godaddyPageUrl"));

        // Click on 'Sign in' Link
        godaddyHomePage.clickSignInLink();

        // Click on 'Create an Account' link and continue to Registration Page
        RegistrationPage registrationPage = godaddyHomePage.clickCreateAnAccountLink();

        // Generate dynamic email + localDateTime
        String localDateTime = godaddyHomePage.generateLocalDateTime();
        String email = prop.getProperty("user") + "+" + localDateTime + "@gmail.com";
        String password = prop.getProperty("user") + "+" + localDateTime;

        // Fill all registration fields, submit and continue to Account page
        AccountPage accountPage = registrationPage.createAccount(email, password);

        // Waiting for specific Account name to be present on page
        accountPage.waitForAccountToBePresent(email, 6);
        String accountName = accountPage.getAccountNameText();

        // Verify that account is created with specific email
        softAssert.assertTrue(accountName.contains(email),
                "Account name does not contain expected email! "
                        + "\nExpected account: " + email
                        + "\nActual account: " + accountName);

        softAssert.assertAll();
    }

    @Test
    public void calendlyTestsTC4() throws Exception {

        // Create Property Reader object to read data
        PropertyReader prop = new PropertyReader();

        SoftAssert softAssert = new SoftAssert();

        // Open Calendly Page
        CalendlySignUpPage calendlySignUpPage = new CalendlySignUpPage(driver, log);
        calendlySignUpPage.openCalendlyUrl(prop.getProperty("calendlyPageUrl"));

        // Generate dynamic email/name/password + localDateTime
        String localDateTime = calendlySignUpPage.generateLocalDateTime();
        String email = prop.getProperty("user") + "+" + localDateTime + "@gmail.com";
        String name = prop.getProperty("user") + "+" + localDateTime;
        String password = prop.getProperty("user") + "+" + localDateTime;

        // Click on "Sign Up" button
        calendlySignUpPage.clickOnSignUpButton();

        // Enter email address
        calendlySignUpPage.enterEmail(email);

        //Enter name and password
        calendlySignUpPage.enterNameAndPassword(name, password);

        // Wait for an email to be received
        sleep(6000);

        // Open Gmail Login Page
        GmailLoginPage gmailLoginPage = new GmailLoginPage(driver, log);
        gmailLoginPage.openGmailLoginPage(prop.getProperty("gmailPageUrl"));

        // Login into Gmail account and continue to Gmail Inbox Page
        String gmailEmail = prop.getProperty("gmailEmail");
        String gmailPassword = prop.getProperty("gmailPassword");
        GmailInboxPage gmailInboxPage = gmailLoginPage.logIn(gmailEmail, gmailPassword);

        // Verify that you are logged in into gmail account with specific email
        String actualEmail = gmailInboxPage.getEmailAddress();
        softAssert.assertEquals(actualEmail, gmailEmail,
                "Gmail account does not contain expected email address!"
                        + "\nExpected email: " + gmailEmail
                        + "\nActual email: " + actualEmail);

        // Find email with specific subject
        String emailSubject = prop.getProperty("emailSubject");
        gmailInboxPage.findEmailWithSpecificSubject(emailSubject);

        // Click on 'Confirm my email' and continue to Calendly Login Page
        CalendlyLoginPage calendlyLoginPage = gmailInboxPage.confirmEmail(email);

        // Switch to child tab (Calendly Login Page)
        calendlyLoginPage.switchToChildTab();

        // Login into Calendly with password
        calendlyLoginPage.logIn(password);

        // Verify option to create custom Calndly URL
        String actualCalendlyURL = calendlyLoginPage.getCustomCalendlyURl();
        String calendlyUser = prop.getProperty("user");
        softAssert.assertTrue(actualCalendlyURL.contains(calendlyUser),
                "Custom Calendly URL does not contain expected user!"
                        + "\nExpected user: " + calendlyUser
                        + "\nActual Calendly URL: " + actualCalendlyURL);

        softAssert.assertAll();
    }
}



