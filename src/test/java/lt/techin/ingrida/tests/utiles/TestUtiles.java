package lt.techin.ingrida.tests.utiles;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class TestUtiles {

    public static void stay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
    }

    public static String getRandomEmail() {
        String lowercase = "abcdefghijklmnopqrstuvwxyz";
        String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String number = "0123456789";
        return RandomStringUtils.random(4, lowercase)
                + RandomStringUtils.random(2, number)
//                + System.currentTimeMillis()
                + "@one.lt";
    }
    public static String getRandomEmailByTime() {
        long timeMillSeconds = new Date().getTime();
        return "user" + timeMillSeconds + "@email.com";
    }

    public static String getRandomEmailSimple() {
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(1000);
        return ("Inga" + randomInt + "@one.lt");
    }

//    public static String getRandomName() {
//        return RandomStringUtils.randomAlphabetic(8);
//    }
//
//    public static String getRandomPassword() {
//        return RandomStringUtils.randomAlphanumeric(10);
//    }
//
//    public static String getRandomNumber() {
//        return RandomStringUtils.randomNumeric(1, 2);
//    }
//    public static String getRandomNumberFrom1To20() {
//        return String.valueOf(ThreadLocalRandom.current().nextInt(1, 21));
//    }
//
//    public static int getRandomNumberFrom1To20_() {
//        Random random = new Random();
//        return random.nextInt(20) + 1;
//    }
}
