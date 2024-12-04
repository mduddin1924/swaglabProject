package base;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import utilities.ExcelReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class Hooks extends Base {

    @Before
    public void setup() throws IOException, InvalidFormatException {

        // cntrl+alt+l  for alignment

        prop = new Properties();
        file = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\config\\app.properties");

        // test for System.getProperty("user.dir")
        System.out.println("Working Directory: " + System.getProperty("user.dir"));

        fis = new FileInputStream(file);
        prop.load(fis);
        excelReader = new ExcelReader();

        switch (prop.getProperty("browser")) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--disable-notifications");
                //chromeOptions.setHeadless(false);
                driver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                //firefoxOptions.addArguments("--disable-notifications"); // browser alert notification
                //firefoxOptions.setHeadLess(false);
                driver = new FirefoxDriver(firefoxOptions);
                break;

            case "ie":
                WebDriverManager.iedriver().setup();
                InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
                //internetExplorerOptions.addArguments("--disable-notifications"); // browser alert notification
                //internetExplorerOptions.setHeadLess(false);
                driver = new InternetExplorerDriver(internetExplorerOptions);
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                //edgeOptions.addArguments("--disable-notifications"); // browser alert notification
                //edgeOptions.setHeadLess(false);
                driver = new EdgeDriver(edgeOptions);
                break;

        }

        url = prop.getProperty("env");
        driver.manage().window().maximize();

        int seconds = Integer.parseInt(prop.getProperty("implicitWait")); // type casting (String to int)
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        // execute by config app.properties...
        userName = prop.getProperty("username");
        userPassword = prop.getProperty("password");

    }

    @After
    public void tearDown(Scenario scenario) throws IOException {
        try {
            String screenshotName = scenario.getName().replace("", "");
            if (scenario.isFailed()) {
                scenario.log("this is my failure message");
                TakesScreenshot ts = (TakesScreenshot) driver;
                byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", screenshotName);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

        fis.close(); // close the file
        // driver.close(); // current browser tab
        driver.quit();    // browser close

    }

}

