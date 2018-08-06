package com.appscharles.libs.databaser.converters;

import java.text.DecimalFormat;

/**
 * The type Bytes representation converter.
 */
public class BytesRepresentationConverter {

    private static final long K = 1024;
    private static final long M = K * K;
    private static final long G = M * K;
    private static final long T = G * K;

    /**
     * Convert string.
     *
     * @param value the value
     * @return the string
     */
    public static String convert(final long value){
        final long[] dividers = new long[] { T, G, M, K, 1 };
        final String[] units = new String[] { "TB", "GB", "MB", "KB", "B" };
        if(value < 1){
            throw new IllegalArgumentException("Invalid file size: " + value);
        }
        String result = null;
        for(int i = 0; i < dividers.length; i++){
            final long divider = dividers[i];
            if(value >= divider){
                result = format(value, divider, units[i]);
                break;
            }
        }
        return result;
    }

    private static String format(final long value, final long divider, final String unit){
        final double result = divider > 1 ? (double) value / (double) divider : (double) value;
        return new DecimalFormat("#,##0.#").format(result) + " " + unit;
    }
}
