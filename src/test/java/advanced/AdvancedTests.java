package advanced;

import base.base.PropertyReader;
import base.base.TestUtilities;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.amazon.AmazonHomePage;
import pages.amazon.AllGiftCardsPage;
import pages.amazon.JustBecauseGiftCardPage;
import pages.botsify.BotsifyHomePage;
import pages.botsify.ConversationPage;
import pages.calendly.*;
import pages.gmail.GmailInboxPage;
import pages.gmail.GmailLoginPage;

public class AdvancedTests extends TestUtilities {

    @Test
    public void amazonTestsTC1() throws Exception {
        // Create Property Reader object to read data
        PropertyReader prop = new PropertyReader();

        // Open Amazon Home Page
        AmazonHomePage amazonHomePage = new AmazonHomePage(driver, log);
        amazonHomePage.openPage(prop.getProperty("amazonPageUrl"));

        // Go to the All list
        amazonHomePage.clickOnAllList();

        // Select 'Gift Cards' from side menu
        amazonHomePage.selectItemFromMenu(prop.getProperty("menuItem"));

        // Select 'All gift cards' and continue to All Gift Cards Page
        AllGiftCardsPage allGiftCardsPage = amazonHomePage.selectItemFromMenu(prop.getProperty("subMenuItem"));

        // Click on 'See All' button
        allGiftCardsPage.shopByOccasion(prop.getProperty("shopByOccasion"));

        // Choose Occasion and continue to Just Because Gift Card Page
        JustBecauseGiftCardPage justBecauseGiftCardPage = allGiftCardsPage.chooseOccasion(prop.getProperty("occasion"));

        // Find Card in Mail Delivery List, click on the card and get price
        justBecauseGiftCardPage.findCardInMailDeliveryList(prop.getProperty("cardName"));
    }

    @Test
    public void calendlyTestsTC2() throws Exception {

        SoftAssert softAssert = new SoftAssert();

        // Create Property Reader object to read data
        PropertyReader prop = new PropertyReader();

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

        // Click on 'Continue without calendar' and continue to Availability Page
        AvailabilityPage availabilityPage = calendlyLoginPage.continueWithoutCalendar();

        // Select Sunday/Wednesday/Saturday for available dates
        String day1 = prop.getProperty("day1");
        String day2 = prop.getProperty("day2");
        String day3 = prop.getProperty("day3");
        availabilityPage.setAvailableDays(day1, day2, day3);

        //Set available hours from 10:00 till 15:00 and continue to Role Page
        String timeFrom = prop.getProperty("timeFrom");
        String timeTo = prop.getProperty("timeTo");
        RolePage rolePage = availabilityPage.setAvailableHours(timeFrom, timeTo);

        // Choose role at work and continue to Event page
        String role = prop.getProperty("role");
        EventPage eventPage = rolePage.chooseRoleAtWork(role);

        // Validate Calendly Event Home page
        String expectedEventPageURL = prop.getProperty("eventPageUrl");
        String actualEventPageURL = eventPage.getEventPageURL();
        softAssert.assertEquals(expectedEventPageURL, actualEventPageURL,
                "Actual event page URL does not contain Expected event page URL!"
                        + "\nExpected page URL: " + expectedEventPageURL
                        + "\nActual page URL : " + actualEventPageURL);

        softAssert.assertAll();
    }

    @Test
    public void botsifyTestsTC3() throws Exception {

        SoftAssert softAssert = new SoftAssert();

        // Create Property Reader object to read data
        PropertyReader prop = new PropertyReader();

        // Open Botsify page URL: https://botsify.com/
        BotsifyHomePage botsifyHomePage = new BotsifyHomePage(driver, log);
        botsifyHomePage.openPage(prop.getProperty("botsifyPageUrl"));

        // Click to start a chat and switch to frame
        ConversationPage conversationPage = botsifyHomePage.clickOnChatBot();
        conversationPage.switchToFrame();

        // Generate dynamic name and email + LocalDateTime
        String localDateTime = conversationPage.generateLocalDateTime();
        String name = prop.getProperty("user") + "+" + localDateTime;
        String email = prop.getProperty("user") + localDateTime + "@gmail.com";

        // Starting conversation with given name + email
        conversationPage.startConversation(name, email);

        // Clicking on 'Features' Button
        conversationPage.clickOnFeaturesButton();

        // Verification: Check the response after clicking on 'Features' button
        // Verification P1
        String expectedP1 = conversationPage.getFeaturesResponse(0);
        String actualP1 = prop.getProperty("P1");
        softAssert.assertEquals(actualP1, expectedP1,
                "Actual text is not equal to expected text! "
                        + "\nExpected P1: " + expectedP1
                        + "\nActual P1: " + actualP1);

        // Verification P2
        String expectedP2 = conversationPage.getFeaturesResponse(1);
        String actualP2 = prop.getProperty("P2");
        softAssert.assertEquals(actualP2, expectedP2,
                "Actual text is not equal to expected text! "
                        + "+\nExpected P2: " + expectedP2
                        + "\nActual P2: " + actualP2);

        // Verification P3
        String expectedP3 = conversationPage.getFeaturesResponse(2);
        String actualP3 = prop.getProperty("P3");
        softAssert.assertEquals(actualP3, expectedP3,
                "Actual text is not equal to expected text! "
                        + "\nExpected P3: " + expectedP3
                        + "\nActual P3: " + actualP3);

        // Click on 'No' button
        conversationPage.clickOnNoButton();

        // Verification: Check the response after clicking on 'No' button
        String expectedP4 = conversationPage.getNoResponse();
        String actualP4 = prop.getProperty("P4");
        softAssert.assertEquals(actualP4, expectedP4,
                "Actual text is not equal to expected text! "
                        + "\nExpected P4: " + expectedP4
                        + "\nActual P4: " + actualP4);

        // Click on 'Pick a Slot' button
        conversationPage.clickOnPickASlot();

        // Switch to child tab -> Click on the bot chat -> Switch to frame
        botsifyHomePage.switchToChildTab();
        botsifyHomePage.clickOnChatBot();
        conversationPage.switchToFrame();

        // Click on the 'main menu' in chat-bot
        conversationPage.clickOnMainMenuChatBot();

        // Click on Delete Conversation
        conversationPage.deleteConversation();

        // Click OK to confirm it
        conversationPage.confirmDelete();

        // Verification: Check the response after clicking on 'Ok' button
        String expectedP5 = conversationPage.getOkResponse();
        String actualP5 = prop.getProperty("P5");
        softAssert.assertEquals(actualP5, expectedP5,
                "Actual text is not equal to expected text! "
                        + "\nExpected P5: " + expectedP5
                        + "\nActual P5: " + actualP5);

        softAssert.assertAll();
    }

}




