package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    WebDriver driver;
    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        System.out.println("This is sample comment for conflict");
    }

    @FindBy(id = "input-email")
    private WebElement emailAddressField;

    @FindBy(id = "input-password")
    private WebElement enterPassword;

    @FindBy(xpath = "//input[@value='Login']")
    private WebElement loginButton;

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    public void enterEmail(String email){
        wait.until(ExpectedConditions.visibilityOf(emailAddressField));
        emailAddressField.sendKeys(email);
    }

    public void enterPassword(String password){
        wait.until(ExpectedConditions.visibilityOf(enterPassword));
        enterPassword.sendKeys(password);
    }

    public AccountPage clickOnLoginButton() {
        wait.until(ExpectedConditions.visibilityOf(loginButton));
        loginButton.click();
        return new AccountPage(driver);
    }
}
