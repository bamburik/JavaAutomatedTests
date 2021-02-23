package PageObjects;

import BusinessObjects.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.regex.Pattern;

public class PageLogin {

    private WebDriver driver;

    @FindBy(how = How.CSS, using = ".qa-show-sidetray-account")
    private WebElement linkShowAccount;

    @FindBy(how = How.CSS, using = "h2.modal-title")
    private WebElement titleAccountModal;

    @FindBy(how = How.CSS, using = ".qa-btn-signin")
    private WebElement buttonSignIn;

    @FindBy(how = How.CSS, using = ".form-input-username")
    private WebElement inputUsername;

    @FindBy(how = How.CSS, using = ".form-input-password")
    private WebElement inputPassword;

    @FindBy(how = How.CSS, using = ".qa-btn-login")
    private WebElement buttonLogin;

    @FindBy(how = How.CSS, using = ".qa-btn-accept-cookie")
    private WebElement buttonAcceptCookie;

    public PageLogin(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void loginWith(User user){
        linkShowAccount.click();
        buttonSignIn.click();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOf(inputUsername));
        inputUsername.sendKeys(user.getEmail());
        inputPassword.sendKeys(user.getPassword());
        buttonLogin.click();
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.textMatches(By.cssSelector("h2.modal-title"), Pattern.compile("/'s Account$/i")));
    }

    public String getAccountModalTitle() {
        return titleAccountModal.getText();
    }

    public void acceptCookie(){
        buttonAcceptCookie.click();
    }
}
