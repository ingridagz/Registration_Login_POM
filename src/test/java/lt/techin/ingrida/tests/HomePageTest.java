package lt.techin.ingrida.tests;

import lt.techin.ingrida.pages.HomePage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;


import static org.slf4j.LoggerFactory.getLogger;
import static java.lang.invoke.MethodHandles.lookup;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HomePageTest extends BasePageTest {

    protected HomePage homePage;

    private static final Logger log = getLogger(lookup().lookupClass());

    @BeforeEach
    void setUpHomePage() {
        homePage = new HomePage(driver);
    }

    @Test
    void goToRegistrationPageTest() {
        log.info("'goToRegistrationPage' initialized");
        homePage.clickButtonCreateAccount();

        String currentURL = driver.getCurrentUrl();
        assertThat(currentURL).as("Expected URL to contain '/register").contains("/register");

        homePage.isRegisterButtonDisplayed();
        Assertions.assertTrue(homePage.isRegisterButtonDisplayed2(), "Register button is not displayed");
        log.info(("'goToRegistrationPage' completed"));
    }
}
