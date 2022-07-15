package mst.shr.imdb.imdbproject.utilities;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class RandomUtilities {

    /**
     * Return random string with specified number of character
     * @param length    number of character, maximum is 20
     * @return  random sequence of characters as a String
     * @throws NoSuchAlgorithmException
     */
    public static String RandomStringGenerator(int length) throws NoSuchAlgorithmException {
        if (length>20)
            length = 20;
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        String currentTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        byte[] digestArray = messageDigest.digest(currentTime.getBytes(StandardCharsets.UTF_8));
        String randomString = Base64.getEncoder().encodeToString(digestArray).replaceAll("[/\\\\\\\\]","").substring(0,length);

        return randomString;
    }
}
