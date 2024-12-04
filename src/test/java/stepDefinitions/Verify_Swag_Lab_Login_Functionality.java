package stepDefinitions;

import base.Base;
import io.cucumber.java.en.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.io.IOException;
//import io.cucumber.java.en.And;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
import pages.HomePage;
import pages.LandingPage;

public class Verify_Swag_Lab_Login_Functionality extends Base {

    @Given("user in the landing page")
    public void user_in_the_landing_page() throws InterruptedException, IOException {

        navigateUrl(url);
        screenShotName(driver,"Landing Page"); // Screen-Shot
        Thread.sleep(1000);

    }

    @And("user enter id and password from sheet {string} and row {int}")
    public void user_enter_id_and_password_from_sheet_and_row(String sheetName, Integer rowNumber) throws IOException, InvalidFormatException, InterruptedException {

        testData = excelReader.getData(excelFilePath, sheetName);

        String userId = testData.get(rowNumber).get("userName");
        sendKeys(LandingPage.userName, userId); // Page Object
        //sendKeys(By.id("user-name"), userName);
        Thread.sleep(1000);

        String userPwd = testData.get(rowNumber).get("passWord");
        sendKeys(LandingPage.userPassword, userPwd); // Page Object
        //sendKeys(By.id("password"), userPwd);
        Thread.sleep(1000);
    }

    @Then("verify the login button color and click on login button")
    public void verify_the_login_button_color_and_click_on_login_button() throws InterruptedException, IOException {

        // login button verification... go to DOM Inspect follow right side window then (Styles)
        // there has key and value (***just follow and try to understand***)
        WebElement element = driver.findElement(By.xpath("//input[@id='login-button']"));

        // get all Web-element (color+other web-elements) by (getCssValue Method)
        String loginBtnColor = element.getCssValue("background-color");

        screenShotName(driver,"Login Button"); // Screen-Shot

        System.out.println(loginBtnColor); // login button color verification
        Thread.sleep(1000);

        click(LandingPage.loginButton); // Page Object
        System.out.println("Sharif");


        // click(By.id("login-button"));  // login button by method

        // we can use this locator without click(). used by sendkeys by enum(Keys) (Keys.ENTER)
        //driver.findElement(By.id("login-button")).sendKeys(Keys.ENTER);
        Thread.sleep(1000);
    }

    @Then("user click on menu button")
    public void user_click_on_menu_button() throws InterruptedException, IOException {

        click(HomePage.burgerMenu); // Page Object
        screenShotName(driver,"Home Page"); // Screen-Shot

        //click(By.id("react-burger-menu-btn")); // burger menu
        Thread.sleep(1000);

    }

    @Then("user click on logout")
    public void user_click_on_logout() throws IOException {

        click(HomePage.logOut); // Page Object

        //click(By.id("logout_sidebar_link")); // logout

    }


}
