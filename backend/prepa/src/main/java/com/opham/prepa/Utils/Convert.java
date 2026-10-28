package com.opham.prepa.Utils;

import org.springframework.util.StringUtils;

import java.math.BigInteger;

public class Convert {
    public static String decimalToHexadecimal(int decimalNumber) {
        BigInteger bigInteger = BigInteger.valueOf(decimalNumber);
        String hexadecimalString = bigInteger.toString(16);
        return StringUtils.capitalize(hexadecimalString.toUpperCase()); // Optional: Capitalize the hexadecimal string
    }

    public static String toIso1(String text) {
        if (text == null) return null;

        String cleaned = text;

        // Apostrophes typographiques → '
        cleaned = cleaned.replace("’", "'").replace("‘", "'");

        // ® → (R)
        cleaned = cleaned.replace("®", "(R)");

        // Espaces insécables → espace normal
        cleaned = cleaned.replace('\u00A0', ' ');

        // Indices → chiffres
        cleaned = cleaned.replace("₀","0").replace("₁","1").replace("₂","2")
                .replace("₃","3").replace("₄","4").replace("₅","5")
                .replace("₆","6").replace("₇","7").replace("₈","8")
                .replace("₉","9");

        // œ → oe
        cleaned = cleaned.replace("œ", "oe").replace("Œ", "Oe");

        // Garder uniquement les caractères Latin1 + ponctuation + chiffres + espace
        cleaned = cleaned.replaceAll("[^\\x00-\\xFF]", "?");

        return cleaned;
    }

    public static String hexToCode(String hex,String x) {
        // 1. Hex → int
        long value = Long.parseLong(hex, 16);

        // 2. long → string
        String str = String.valueOf(value);


        if (str.endsWith(x)) {
            str = str.substring(0, str.length() - 1);
        }


        return str+"000"+x+"0";
    }
}
