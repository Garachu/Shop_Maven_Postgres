package com.shop;

import org.springframework.http.MediaType;

import java.nio.charset.Charset;
import java.util.Random;

/**
 * Created by meg on 8/17/17.
 */
public class TestUtil {

    public static final MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    public static String generateRandomStr() {
        StringBuilder str = new StringBuilder("test_");
        final String alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final int N = alphabet.length();
        Random r = new Random();
        for (int i = 0; i < 3; i++) {
            str.append(alphabet.charAt(r.nextInt(N)));
        }
        return str.toString();
    }

}
