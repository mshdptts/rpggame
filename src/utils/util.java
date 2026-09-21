package utils;

import java.util.Random;
import java.util.Scanner;

public class util {
    public static Scanner scanner = new Scanner(System.in);
    private static final Random rand = new Random();

    public static String getline(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine();
        return input;
    }

    public static void banner(String title) {
        System.out.println();
        System.out.println("========================================");
        System.out.println("              " + title);
        System.out.println("========================================");
    }

    public static void section(String title) {
        System.out.println();
        System.out.println("--- " + title + " ---");
    }

    public static void menuOption(String key, String label) {
        System.out.println("  [" + key + "] " + label);
    }

    public static String slowPrint(String text, int delay) {
        for (char c : text.toCharArray()) {
            System.out.print(c);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();
        return text;
    }

    public static boolean chance(double probability) {
        return rand.nextDouble() < probability;
    }

    public static int randomInt(int minInclusive, int maxExclusive) {
        return minInclusive + rand.nextInt(maxExclusive - minInclusive);
    }
}