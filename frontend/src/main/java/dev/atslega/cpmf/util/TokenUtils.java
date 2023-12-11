package dev.atslega.cpmf.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class TokenUtils {
    private static final String TOKEN_FILE_PATH = "user_token.txt";

    public static void saveToken(String token) {
        try {
            Files.write(Paths.get(TOKEN_FILE_PATH), token.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readToken() {
        try {
            return new String(Files.readAllBytes(Paths.get(TOKEN_FILE_PATH)));
        } catch (IOException e) {
            return null;
        }
    }
}
