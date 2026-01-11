package ddtPackage;
import Base.Base;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobjects.AccountPage;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import util.DataUtil;
import util.MyXLSReader;
import java.util.HashMap;

public class Login extends Base{
    WebDriver driver;
    MyXLSReader myXLSReader = null;


    @Test(dataProvider = "dataSupplier")
    public void testLogin(HashMap<String, String> map) {
        if(!DataUtil.isRunnable(myXLSReader, "LoginTest", "TestCases") || map.get("Runmode").equals("N")){
            throw new SkipException("Runmode is set to N, Hence test case is skipped");
        }

        driver = openBrowser(map.get("Browser"));
        driver.get(properties.getProperty("url"));
        HomePage homePage = new HomePage(driver);
        homePage.clickOnMyAccountDropMenu();
        LoginPage loginPage = homePage.selectLoginOption();

        loginPage.enterEmail(map.get("Username"));
        loginPage.enterPassword(map.get("Password"));
        AccountPage accountPage = loginPage.clickOnLoginButton();

        String expectedResult = map.get("ExpectedResult");
        boolean expectedConvertedResult = false;
        if(expectedResult.equalsIgnoreCase("Success")){
            expectedConvertedResult = true;
        }
        else if(expectedResult.equalsIgnoreCase("Failure")){
            expectedConvertedResult = false;
        }

        boolean actualResult = accountPage.verifyDisplayOfEditYourAccountInformationOption();

        Assert.assertEquals(actualResult, expectedConvertedResult);
    }

    @DataProvider
    public Object[][] dataSupplier() {
        Object[][] data = null;
        try {
            myXLSReader = new MyXLSReader("src\\test\\resources\\TutorialsNinja.xlsx");
            data = DataUtil.getTestData(myXLSReader, "LoginTest", "Data");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
