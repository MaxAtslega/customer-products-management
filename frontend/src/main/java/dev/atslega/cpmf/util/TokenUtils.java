package dev.atslega.cpmf.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class TokenUtils {
    private static final String TOKEN_FILE_PATH = "user_token.txt";

    public static void saveToken(String token) {
        try {
            Path path = Paths.get(TOKEN_FILE_PATH);
            if (token != null && !token.isEmpty()) {
                Files.write(path, token.getBytes(), StandardOpenOption.CREATE);
            } else {
                deleteToken();
            }
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

    public static void deleteToken() {
        try {
            Path path = Paths.get(TOKEN_FILE_PATH);
            Files.deleteIfExists(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
