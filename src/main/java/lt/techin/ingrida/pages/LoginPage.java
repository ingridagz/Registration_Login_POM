package lt.techin.ingrida.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;

import java.util.List;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

public class LoginPage extends BasePage {
    private static final Logger log = getLogger(lookup().lookupClass());
    @FindBy(css = ".text-decoration-none")
    WebElement labelClickHereToLogin;
    @FindBy(css = "input#email")
    WebElement inputEmailLogin;
    @FindBy(css = "input#password")
    WebElement inputPasswordLogin;
    @FindBy(css = ".btn-primary")
    WebElement buttonLogin;
    @FindBy(css = ".btn-outline-danger")
    WebElement buttonLogout;
    @FindBy(css = ".toast-body")
    WebElement errorLoginPassword;
    @FindBy(xpath = "//a[@href='/notes/app/profile']")
    WebElement buttonProfile;
    @FindBy(css = "input#user-email")
    WebElement profileEmailAddress;


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void clickHereToLogin() {
        labelClickHereToLogin.click();
    }

    public void fillLoginEmail(String loginEmailField) {
        inputEmailLogin.sendKeys(loginEmailField);
    }

    public void fillLoginPassword(String loginPasswordField) {
        inputPasswordLogin.sendKeys(loginPasswordField);
    }

    public void clickButtonLogin() {
        buttonLogin.click();
    }

    public void isLogoutButtonDisplayed() {
//        return buttonLogout.isDisplayed();
        try {
            buttonLogout.isDisplayed();
        } catch (Exception e) {
            log.error("Logout button is not displayed {}", e.getMessage());
        }
    }

    public boolean isLogoutButtonDisplayed2() {
        return buttonLogout.isDisplayed();
    }

    public void clickButtonLogout() {
        buttonLogout.click();
    }

    public String getErrorMessageLogin() {
        return errorLoginPassword.getText();
    }


    public void clickButtonProfile() {
        buttonProfile.click();
    }

    public String getProfileEmailAddress() {
        return profileEmailAddress.getAttribute("value");
    }


    //    papildomai:
    @FindBy(css = " .btn.btn-lg.btn-primary")
    WebElement getButtonLogin;

    public void clickButtonLoginHomePage() {
        getButtonLogin.click();
    }

    @FindBy(linkText = "Create a free account!")
    WebElement labelCreate;

    public String getFreeWord() {
        String webEl = labelCreate.getText();
        return webEl.split(" ")[3];
    }

    @FindBy(css = " .invalid-feedback")
    List<WebElement> errorLoginMessages;

    public boolean isErrorMessageLoginDisplayed(String messageErr) {
        return errorLoginMessages.stream()
                .map(WebElement::getText)
                .anyMatch(text -> text.equals(messageErr));
    }

}