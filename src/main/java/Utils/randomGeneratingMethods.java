package Utils;

import org.apache.commons.lang.RandomStringUtils;

import java.security.SecureRandom;

public class randomGeneratingMethods {

    public static String getRandomString(int length,boolean useLetters, boolean useNumbers )
    {
        return RandomStringUtils.random(length, useLetters, useNumbers);
    }

}
