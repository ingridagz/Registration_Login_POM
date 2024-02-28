package lt.techin.ingrida.tests;

import lt.techin.ingrida.pages.HomePage;
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

public class RegisterPageTest extends BasePageTest{

    protected HomePage homePage;
    protected RegisterPage registerPage;
    private final String name="Inga";
    private final String password="666666";
    private static final Logger log = getLogger(lookup().lookupClass());

    @BeforeEach
    void setUpRegisterPage() {
        homePage = new HomePage(driver);
        registerPage = new RegisterPage(driver);
    }

    @Test
    void userRegistration1Test() {
        log.info("'userRegistration1Test' initialized");
        homePage.clickButtonCreateAccount();

        String randomEmail1 = TestUtiles.getRandomEmailSimple();
        System.out.println(randomEmail1);

        registerPage.fillEmail(randomEmail1);
//        System.out.println(randomEmail1);
        registerPage.fillName(name);
        registerPage.fillPassword(password);
        registerPage.confirmPassword(password);
        registerPage.clickButtonRegister();
        Assertions.assertEquals("User account created successfully", registerPage.getSuccessfulMessageRegistration(),"Cannot create account");
        log.info(("'userRegistration1Test' completed"));
    }

    @Test
    void userRegistration2Test(){
        log.info("'userRegistration2Test' initialized");
        homePage.clickButtonCreateAccount();

        String randomEmail2 = TestUtiles.getRandomEmail();
        System.out.println(randomEmail2);

        registerPage.fillEmail(randomEmail2);
        registerPage.fillName(name);
        registerPage.fillPassword(password);
        registerPage.confirmPassword(password);
        registerPage.clickButtonRegister();
        Assertions.assertEquals("User account created successfully", registerPage.getSuccessfulMessageRegistration(),"Cannot create account");
        log.info(("'userRegistration2Test' completed"));
    }

    @Test
    void userRegistrationWrongEmailTest(){
        log.info("'userRegistrationWrongEmailTest' initialized");
        homePage.clickButtonCreateAccount();
        registerPage.fillEmail("111111111");
        registerPage.fillName(name);
        registerPage.fillPassword(password);
        registerPage.confirmPassword(password);
        registerPage.clickButtonRegister();
        Assertions.assertEquals("Email address is invalid", registerPage.getErrorMessageEmail(),"User account created successfully");
        log.info(("'userRegistrationWrongEmailTest' completed"));
    }

    @Test
    void userEmptyRegistrationTest(){
        log.info("'userEmptyRegistrationTest' initialized");
        homePage.clickButtonCreateAccount();

        registerPage.clickButtonRegister();
        Assertions.assertTrue(registerPage.isEmptyFormPossible(), "No error messages");
        log.info(("'userEmptyRegistrationTest' completed"));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/register.csv", numLinesToSkip = 1)
    void userRegistrationWithWrongCredentialsTest(String emailCsv, String nameCsv, String passwordCsv, String passwordConfirmCsv, String messageErrCsv) {
        log.info("'userRegistrationWithWrongCredentialsTest' initialized");
        homePage.clickButtonCreateAccount();

        registerPage.fillEmail(emailCsv);
        registerPage.fillName(Objects.requireNonNullElse(nameCsv, ""));
        registerPage.fillPassword(passwordCsv);
        registerPage.confirmPassword(passwordConfirmCsv);

        registerPage.clickButtonRegister();
        Assertions.assertTrue(registerPage.isErrorMessageDisplayed(messageErrCsv), "No error message");
        System.out.println("Error message: " + messageErrCsv);
        log.info("'userRegistrationWithWrongCredentialsTest' completed: "+emailCsv+" / "+nameCsv+" / "+passwordCsv+" / "+passwordConfirmCsv+" / "+messageErrCsv);
    }
}
