package lt.techin.ingrida.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

public class HomePage extends BasePage{

    private static final Logger log = getLogger(lookup().lookupClass());

    @FindBy(css = ".btn-outline-secondary")
    WebElement buttonCreateAccount;
    @FindBy(css = ".btn-primary")
    WebElement buttonRegister;


    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickButtonCreateAccount() {
        buttonCreateAccount.click();
    }

    public void isRegisterButtonDisplayed() {
        try{
            buttonRegister.isDisplayed();
        } catch (Exception e){
            log.error("Register button is not displayed {}",e.getMessage());
        }
    }

    public boolean isRegisterButtonDisplayed2() {
        return buttonRegister.isDisplayed();
    }
}
