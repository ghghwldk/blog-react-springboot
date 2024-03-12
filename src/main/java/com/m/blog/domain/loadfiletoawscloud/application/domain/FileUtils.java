package com.m.blog.domain.loadfiletoawscloud.application.domain;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;

public class FileUtils{

    private FileUtils() {
        throw new IllegalStateException("FileUtils class");
    }

    public static File convertBase64ToFile(String base64Content, String filename) {
        byte[] decodedContent = Base64.getDecoder().decode(base64Content.getBytes(StandardCharsets.UTF_8));
        return bytesToFile(decodedContent, filename);
    }

    public static File bytesToFile(byte[] content, String fileName) {
        File file = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(content);
        } catch (IOException e) {
            return null;
        }
        return file;
    }

    public static byte[] convertInputStreamToByte(InputStream inputStream) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        return result.toByteArray();
    }

    //Need to replace all special characters with underscores + add timestamp to filename to make it unique
    public static String generateFileName(String fileName) {
        String name = fileName.replaceAll("[^a-zA-Z0-9.-]", "_");
        return (UUID.randomUUID() + "_" + name);
    }
}
