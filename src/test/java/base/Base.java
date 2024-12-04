package base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import utilities.ExcelReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;


public class Base {
    public static WebDriver driver; //static for reuse for another class
    public static FileInputStream fis; //for file data read used fis.
    public static Properties prop; // used for config/ app.properties by key pair value.
    public static File file; // save every directory's path
    public static ExcelReader excelReader; // for excel reader methods

    // here List is an Interface and (testData) is reference object variable
    public static List<Map<String, String>> testData; // for multiple user used List (collection) <hashMap>
    public static String excelFilePath = System.getProperty("user.dir") + "\\src\\test\\resources\\testData\\loginDataSwagLab.xlsx";
    public static String url;
    public static String userName; // for config use
    public static String userPassword; // for config use


    public static void navigateUrl(String url) {

        driver.get(url);

    }

    public static void click(By locator) {
        driver.findElement(locator).click();
    }

    public static void sendKeys(By locator, String keys) {
        driver.findElement(locator).sendKeys(keys);
    }

    public static String getText(By locator) {
        String s = driver.findElement(locator).getText();
        return s;


    }

    public static void dropDownText(By locator, String text) {
        Select select = new Select(driver.findElement(locator));
        select.selectByVisibleText(text);
    }

    public static void dropDownIndex(By locator, int index) {
        Select select = new Select(driver.findElement(locator));
        select.selectByIndex(index);
    }

    public static void dropDownValue(By locator, String value) {
        Select select = new Select(driver.findElement(locator));
        select.selectByValue(value);
    }

    public static void clear(By locator) {

        driver.findElement(locator).clear();
    }

    public static void screenShotName(WebDriver driver, String name) throws IOException {
        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source, new File(".\\screenShot\\" + name + ".png"));
        //FileUtils.copyFile(source,new File(".\\src\\test\\resources\\capture\\"+name+".png"));
        // FileUtils.copyFile(source,new File(".\\screenShot\\"+name+".png"));
        //FileUtils.copyFile(source,new File("\\ \\"+name+".png"));


    }


}