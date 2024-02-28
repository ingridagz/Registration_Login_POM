package lt.techin.ingrida.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;

import java.util.List;
import java.util.Random;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

public class RegisterPage extends BasePage{
    private static final Logger log = getLogger(lookup().lookupClass());
    @FindBy(xpath = "//input[@id='email']")
    WebElement inputEMail;
    @FindBy(xpath = "//input[@id='name']")
    WebElement inputName;
    @FindBy(xpath = "//input[@id='password']")
    WebElement inputPassword;
    @FindBy(xpath = "//input[@id='confirmPassword']")
    WebElement inputConfirmPassword;
    @FindBy(css = ".btn-primary")
    WebElement buttonRegister;
    @FindBy(xpath= "//b")
    WebElement successfulRegistrationMessage;
    @FindBy(xpath = "//form/div[1]/div[1]/div[1]/div[@class='invalid-feedback']")
    WebElement errorInputEmailMessage;
    @FindBy(css = ".invalid-feedback")
    WebElement errorEmptyRegistrationMessages;
    @FindBy(xpath = "//div[@class='invalid-feedback']")
    List<WebElement> errorRegistrationMessages;


    public RegisterPage(WebDriver driver) {
        super(driver);
    }


    public void fillEmail(String email) {
        inputEMail.sendKeys(email);
    }

    public void fillName(String name) {
        inputName.sendKeys(name);
    }

    public void fillPassword(String password) {
        inputPassword.sendKeys(password);
    }

    public void confirmPassword(String confirmPassword) {
        inputConfirmPassword.sendKeys(confirmPassword);
    }

    public void clickButtonRegister() {
        buttonRegister.click();
    }

    public String getSuccessfulMessageRegistration() {
        return successfulRegistrationMessage.getText();
    }

    public String getErrorMessageEmail() {
        return errorInputEmailMessage.getText();
    }

    public boolean isEmptyFormPossible() {
        return errorEmptyRegistrationMessages.isDisplayed();
    }

    public boolean isErrorMessageDisplayed(String messageErr) {
        return errorRegistrationMessages.stream()
                .map(WebElement::getText)
                .anyMatch(text -> text.equals(messageErr));

//        ArrayList<String> displayedErrorMessages= new ArrayList<>();
//        for (WebElement errorMessage : errorRegistrationMessages) {
//            displayedErrorMessages.add(errorMessage.getText());
//        }
//        return displayedErrorMessages.contains(errorMessageText);
    }
}
