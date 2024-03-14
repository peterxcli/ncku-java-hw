package hw2;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Hw2 {
    private static long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    private static String fractionToRepeatingDecimal(long numerator, long denominator) {
        if (numerator == 0) return "0";
        StringBuilder result = new StringBuilder();

        if (numerator < 0 ^ denominator < 0) result.append("-");

        numerator = Math.abs(numerator);
        denominator = Math.abs(denominator);

        result.append(numerator / denominator);
        long remainder = numerator % denominator;
        if (remainder == 0) return result.toString();

        result.append(".");
        Map<Long, Integer> remainderPositions = new HashMap<>();
        while (remainder != 0) {
            if (remainderPositions.containsKey(remainder)) {
                int pos = remainderPositions.get(remainder);
                return result.substring(0, pos) + result.substring(pos);
            }
            remainderPositions.put(remainder, result.length());
            remainder *= 10;
            result.append(remainder / denominator);
            remainder %= denominator;
        }
        return result.toString();
    }

    private static long[] sumFractions(long[] frac1, long[] frac2) {
        long lcm = lcm(frac1[1], frac2[1]);
        long num = (frac1[0] * (lcm / frac1[1])) + (frac2[0] * (lcm / frac2[1]));
        long denom = lcm;

        long gcd = gcd(num, denom);
        num /= gcd;
        denom /= gcd;

        return new long[]{num, denom};
    }

    private static long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            String x = scanner.next();
            String y = scanner.next();

            long[] fractionX = repeatingDecimalToFraction(x);
            long[] fractionY = repeatingDecimalToFraction(y);
            long[] sum = sumFractions(fractionX, fractionY);

            String result = fractionToRepeatingDecimal(sum[0], sum[1]);
            System.out.println(result);
        }
        scanner.close();
    }

    private static long[] repeatingDecimalToFraction(String decimal) {
        int dotIndex = decimal.indexOf('.');
        long wholePart = Long.parseLong(decimal.substring(0, dotIndex));
        String repeatingPart = decimal.substring(dotIndex + 1);

        long numerator = Long.parseLong(repeatingPart);
        long denominator = (long) Math.pow(10, repeatingPart.length()) - 1;

        long gcd = gcd(numerator, denominator);
        numerator /= gcd;
        denominator /= gcd;

        numerator += wholePart * denominator;

        return new long[]{numerator, denominator};
    }
}
