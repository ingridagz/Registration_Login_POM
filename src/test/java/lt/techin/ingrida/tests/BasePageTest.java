package lt.techin.ingrida.tests;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;

import java.time.Duration;

public class BasePageTest {

    protected static WebDriver driver;

    private static final Logger log = getLogger(lookup().lookupClass());

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        String URL="https://practice.expandtesting.com/notes/app";
        driver.get(URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        log.info("Navigated to {}", URL);
    }

//    @AfterEach
//    public void tearDown() {
//        driver.close();
//    }


}
