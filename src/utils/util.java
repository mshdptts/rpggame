package utils;

import java.util.Scanner;

public class util {
    public static Scanner scanner = new Scanner(System.in);

    public static String getline(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine();
        return input;
    }
}