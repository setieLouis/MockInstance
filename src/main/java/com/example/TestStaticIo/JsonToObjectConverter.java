package com.example.TestStaticIo;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonToObjectConverter {

    public <T> T convert(String path, Class<T> clazz) throws IOException {
        ObjectMapper mapper =  new ObjectMapper();
        return mapper.readValue(retrieveJsonFile(path), clazz);
    }

    private String retrieveJsonFile(String fileName) throws IOException {
        File file = ResourceUtils.getFile("classpath:" + fileName);
        return new String(Files.readAllBytes(Paths.get(file.getPath())));
    }
}
