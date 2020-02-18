package com.amongalen.buildsvault.util;

import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;

import java.io.IOException;

import java.util.zip.DataFormatException;

import java.util.zip.Deflater;

import java.util.zip.Inflater;

@Slf4j
public final class CompressionUtils {

    private static final int KILOBYTE = 1024;

    public static byte[] compress(byte[] data) throws IOException {
        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        byte[] output;
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length)) {
            deflater.finish();
            byte[] buffer = new byte[KILOBYTE];
            while (!deflater.finished()) {
                int count = deflater.deflate(buffer); // returns the generated code... index
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
            output = outputStream.toByteArray();
        }
        return output;
    }

    public static byte[] decompress(byte[] data) throws IOException, DataFormatException {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        byte[] output;
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length)) {
            byte[] buffer = new byte[KILOBYTE];
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
            output = outputStream.toByteArray();
        }
        return output;
    }

}