package com.opham.prepa.Utils;

import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FileParser {

    private BufferedReader openReader(String filePath) throws IOException {
        return new BufferedReader(
                new InputStreamReader(new ClassPathResource(filePath).getInputStream(), StandardCharsets.UTF_8));
    }

    public List<String> extractNames(String filePath) {
        List<String> names = new ArrayList<>();

        try (BufferedReader br = openReader(filePath)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" -> ");
                if (parts.length >= 1) {
                    names.add(parts[0]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return names;
    }



    public String recupererIP(String name, String filePath) {
        try (BufferedReader br = openReader(filePath)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" -> ");
                if (parts.length == 2 && parts[0].equals(name)) {
                    return parts[1];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; // Retourne null si le nom n'a pas été trouvé
    }
}
