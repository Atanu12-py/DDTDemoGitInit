package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    WebDriver driver;
    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[text() = 'My Account']")
    private WebElement myAccountDropMenu;

    @FindBy(linkText = "Login")
    private WebElement loginOption;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    public void clickOnMyAccountDropMenu(){
        wait.until(ExpectedConditions.visibilityOf(myAccountDropMenu));
        myAccountDropMenu.click();
    }

    public LoginPage selectLoginOption(){
        wait.until(ExpectedConditions.visibilityOf(loginOption));
        loginOption.click();
        return new LoginPage(driver);
    }
}
