package Base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class Base {
    public Properties properties;
    WebDriver driver;

    public WebDriver openBrowser(String browserName){
        properties = new Properties();
        File file = new File("src\\test\\resources\\config.properties");
        try {
            FileInputStream fis = new FileInputStream(file);
            properties.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(browserName.equalsIgnoreCase("Chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=new");      // Use new headless mode
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--disable-gpu");       // optional, improves stability
            options.addArguments("--no-sandbox");        // optional, sometimes needed in CI/CD

            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();          // <-- pass options here!
        }
        else if(browserName.equalsIgnoreCase("Firefox")){
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=new");      // Use new headless mode
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--disable-gpu");       // optional, improves stability
            options.addArguments("--no-sandbox");        // optional, sometimes needed in CI/CD

            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        else if(browserName.equalsIgnoreCase("Edge")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(properties.getProperty("url"));

        return driver;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
