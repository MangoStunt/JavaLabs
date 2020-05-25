package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Coder {
    public static void Encode() throws IOException {
        String content = new String(Files.readAllBytes(Paths.get("files/text.txt")));
        StringBuilder sb = new StringBuilder();
        for (char c : content.toCharArray()) {
            sb.append(++c);
        }
        File file = new File("files/encoded.txt");
        file.createNewFile();
        FileWriter writer = new FileWriter(file);
        writer.write(sb.toString());
        writer.flush();
        writer.close();
    }

    public static void Decode() throws IOException {
        String content = new String(Files.readAllBytes(Paths.get("files/encoded.txt")));
        StringBuilder sb = new StringBuilder();
        for (char c : content.toCharArray()) {
            sb.append(--c);
        }
        File file = new File("files/decoded.txt");
        file.createNewFile();
        FileWriter writer = new FileWriter(file);
        writer.write(sb.toString());
        writer.flush();
        writer.close();
    }
}
