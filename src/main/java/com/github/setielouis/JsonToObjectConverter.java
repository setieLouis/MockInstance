package com.github.setielouis;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@UtilityClass
public class JsonToObjectConverter {

    public <T> T convert(String path, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(retrieveJsonFile(path), clazz);
    }

    public <T> T convertJson(String jsonCntente, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonCntente, clazz);
    }

    public String retrieveJsonFile(String fileName) throws IOException {
        File file = ResourceUtils.getFile("classpath:" + fileName);
        return new String(Files.readAllBytes(Paths.get(file.getPath())));
    }
}
