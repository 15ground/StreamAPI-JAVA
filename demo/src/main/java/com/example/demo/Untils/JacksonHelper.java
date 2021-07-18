package com.example.demo.Untils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonHelper {
    public static <T> List<T> readJsonFileToObject(Class<T> anyClass, String sourceFile) {

        List<T> result = new ArrayList<>();

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(new File(sourceFile));

            rootNode.iterator().forEachRemaining(nodeItem -> {
                result.add(mapper.convertValue(nodeItem, anyClass));
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
