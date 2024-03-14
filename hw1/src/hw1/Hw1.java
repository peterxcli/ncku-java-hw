package hw1;

import java.util.Scanner;

public class Hw1 {
    public static String addLargeNumbers(String num1, String num2) {
        String[] parts1 = num1.split("\\.");
        String[] parts2 = num2.split("\\.");
        String intPart1 = parts1[0];
        String intPart2 = parts2[0];
        String fracPart1 = parts1.length > 1 ? parts1[1] : "";
        String fracPart2 = parts2.length > 1 ? parts2[1] : "";

        while (fracPart1.length() < fracPart2.length())
            fracPart1 += "0";
        while (fracPart2.length() < fracPart1.length())
            fracPart2 += "0";

        // Add the fractional parts
        StringBuilder fracResult = new StringBuilder();
        int carry = 0;
        for (int i = fracPart1.length() - 1; i >= 0; i--) {
            int sum = (fracPart1.charAt(i) - '0') + (fracPart2.charAt(i) - '0') + carry;
            carry = sum / 10;
            fracResult.insert(0, sum % 10);
        }

        StringBuilder intResult = new StringBuilder();
        int maxIntLength = Math.max(intPart1.length(), intPart2.length());
        for (int i = 0; i < maxIntLength; i++) {
            int digit1 = i < intPart1.length() ? intPart1.charAt(intPart1.length() - 1 - i) - '0' : 0;
            int digit2 = i < intPart2.length() ? intPart2.charAt(intPart2.length() - 1 - i) - '0' : 0;
            int sum = digit1 + digit2 + carry;
            carry = sum / 10;
            intResult.insert(0, sum % 10);
        }

        if (carry > 0) {
            intResult.insert(0, carry);
        }

        // while (fracResult.length() > 0 && fracResult.charAt(fracResult.length() - 1) == '0') {
        //     fracResult.deleteCharAt(fracResult.length() - 1);
        // }

        return intResult.toString() + (fracResult.length() > 0 ? "." + fracResult.toString() : "");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            String num1 = parts[0];
            String num2 = parts[1];
            System.out.println(addLargeNumbers(num1, num2));
        }

        scanner.close();
    }
}
