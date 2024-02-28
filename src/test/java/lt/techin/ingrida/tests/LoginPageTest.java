package lt.techin.ingrida.tests;

import lt.techin.ingrida.pages.HomePage;
import lt.techin.ingrida.pages.LoginPage;
import lt.techin.ingrida.pages.RegisterPage;
import lt.techin.ingrida.tests.utiles.TestUtiles;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.slf4j.Logger;

import java.util.Objects;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

public class LoginPageTest extends BasePageTest {

    private HomePage homePage;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private final String name = "Inga";
    private final String password = "123456";

    private static final Logger log = getLogger(lookup().lookupClass());

    @BeforeEach
    void setUpLoginPage() {
        homePage = new HomePage(driver);
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
    }

    @Test
    void goToLoginPageTest() {
        log.info("'goToLoginPageTest' initialized");
        homePage.clickButtonCreateAccount();

        String getRandomEmail2 = TestUtiles.getRandomEmail();
        registerPage.fillEmail(getRandomEmail2);
        System.out.println(getRandomEmail2);
        registerPage.fillName(name);
        registerPage.fillPassword(password);
        registerPage.confirmPassword(password);
        registerPage.clickButtonRegister();

        loginPage.clickHereToLogin();
        loginPage.fillLoginEmail(getRandomEmail2);
        System.out.println(getRandomEmail2);
        loginPage.fillLoginPassword(password);
        loginPage.clickButtonLogin();

        loginPage.isLogoutButtonDisplayed();
        Assertions.assertTrue(loginPage.isLogoutButtonDisplayed2(),"Register button is not displayed");
        System.out.println(loginPage.isLogoutButtonDisplayed2());

        loginPage.clickButtonLogout();
        log.info("'goToLoginPageTest' completed");
    }

    @Test
    void goToLoginPageWithWrongPasswordTest() {
        log.info("'goToLoginPageWithWrongPasswordTest' initialized");
        homePage.clickButtonCreateAccount();

        String getRandomEmail2 = TestUtiles.getRandomEmail();
        registerPage.fillEmail(getRandomEmail2);
        registerPage.fillName(name);
        registerPage.fillPassword(password);
        registerPage.confirmPassword(password);
        registerPage.clickButtonRegister();

        loginPage.clickHereToLogin();
        loginPage.fillLoginEmail(getRandomEmail2);
        loginPage.fillLoginPassword("555555");
        loginPage.clickButtonLogin();

        Assertions.assertEquals("Incorrect email address or password", loginPage.getErrorMessageLogin(),"No error message");
        log.info("'goToLoginPageWithWrongPasswordTest' completed");
    }

    @Test
    void userLoginFromFile() {
        log.info("'goToProfileCheckEmailTest' initialized");
        homePage.clickButtonCreateAccount();

        String randomEmail2 = TestUtiles.getRandomEmailSimple();
        registerPage.fillEmail(randomEmail2);
        registerPage.fillName(name);
        registerPage.fillPassword(password);
        registerPage.confirmPassword(password);
        registerPage.clickButtonRegister();

        loginPage.clickHereToLogin();
        loginPage.fillLoginEmail(randomEmail2);
        loginPage.fillLoginPassword(password);
        loginPage.clickButtonLogin();

        loginPage.clickButtonProfile();
        Assertions.assertEquals(randomEmail2, loginPage.getProfileEmailAddress(), "Wrong Email Address");
        log.info("'goToProfileCheckEmailTest' completed");
    }




//    papildomai:

    @Test
    void getFreeWordTest() {
        homePage.clickButtonCreateAccount();

        String getRandomEmail2 = TestUtiles.getRandomEmail();
        registerPage.fillEmail(getRandomEmail2);
        registerPage.fillName(name);
        registerPage.fillPassword(password);
        registerPage.confirmPassword(password);
        registerPage.clickButtonRegister();

        loginPage.clickHereToLogin();
        System.out.println(loginPage.getFreeWord());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/loginPassword.csv", numLinesToSkip = 1)
    void userLoginWithWrongPasswordTest(String passwordCsv,String messageErrCsv) {
        log.info("'userLoginWithWrongPasswordTest' initialized");
        homePage.clickButtonCreateAccount();

        String randomEmail2 = TestUtiles.getRandomEmailSimple();
        registerPage.fillEmail(randomEmail2);
        registerPage.fillName("meska");
        registerPage.fillPassword("123456");
        registerPage.confirmPassword("123456");
        registerPage.clickButtonRegister();

        loginPage.clickHereToLogin();
        loginPage.fillLoginEmail(randomEmail2);
//        loginPage.fillLoginPassword(passwordCsv);

        loginPage.fillLoginPassword(Objects.requireNonNullElse(passwordCsv, ""));
        loginPage.clickButtonLogin();

        Assertions.assertTrue(loginPage.isErrorMessageLoginDisplayed(messageErrCsv), "No error message");
        System.out.println("Error message: " + messageErrCsv);

        log.info("'userLoginWithWrongPasswordTest' completed");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/loginEmail.csv", numLinesToSkip = 1)
    void userLoginWithWrongEmail(String emailCsv,String messageErrCsv) {
        log.info("'userLoginWithWrongEmail' initialized");
        loginPage.clickButtonLoginHomePage();

        loginPage.fillLoginPassword("111111");

        loginPage.fillLoginEmail(Objects.requireNonNullElse(emailCsv, ""));
        loginPage.clickButtonLogin();

        Assertions.assertTrue(loginPage.isErrorMessageLoginDisplayed(messageErrCsv), "No error message");
        System.out.println("Error message: " + messageErrCsv);

        log.info("'userLoginWithWrongEmail' completed");
    }

//sitas geras:

    @ParameterizedTest
    @CsvFileSource(resources = "/login.csv", numLinesToSkip = 1)
    void userLoginWithWrongCredentials(String emailCsv,String passwordCsv,String messageErrCsv) {
        log.info("'userLoginWithWrongEmail' initialized");
        loginPage.clickButtonLoginHomePage();

        loginPage.fillLoginEmail(Objects.requireNonNullElse(emailCsv, ""));
        loginPage.fillLoginPassword(Objects.requireNonNullElse(passwordCsv, ""));
        loginPage.clickButtonLogin();

        Assertions.assertTrue(loginPage.isErrorMessageLoginDisplayed(messageErrCsv), "No error message");
//        System.out.println("Error message: " + messageErrCsv);

        log.info("'userLoginWithWrongEmail' completed: "+emailCsv+" / "+passwordCsv+" / "+messageErrCsv);
    }
}


