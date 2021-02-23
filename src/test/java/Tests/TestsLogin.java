package Tests;

import BusinessObjects.User;
import PageObjects.PageLogin;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestsLogin {

    WebDriver driver;
    User user;

    @BeforeClass
    public void preparation() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Valerii_Bamburov\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe");
        user = new User("bamburov.001@mail.ru", "Valerii", "P@ssw0rd");
        //WebDriverManager.chromedriver().version("88.0.4324.96").setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("enable-automation");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-browser-side-navigation");
        options.addArguments("--disable-gpu");
        options.addArguments("--dns-prefetch-disable");
        options.addArguments("test-type");
        options.addArguments("disable-popup-blocking");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://www.ae.com");
    }

    @Test
    public void testLogin() {
        PageLogin pageLogin = new PageLogin(driver);
        pageLogin.acceptCookie();
        pageLogin.loginWith(user);
        Assert.assertEquals(pageLogin.getAccountModalTitle(), user.getUsername() + "'s Account");
    }

    @AfterClass
    public void CleanUp(){
        driver.quit();
    }
}
