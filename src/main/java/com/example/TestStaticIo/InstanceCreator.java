package com.example.TestStaticIo;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.TestStaticIo.JsonToObjectConverter.*;

public class InstanceCreator {

    Map<String , String> jsonFiles = new HashMap<>();

    public InstanceCreator(String s) throws IOException {
        String fileContent =  retrieveJsonFile(s);
        String fileName = getFileName(s);
        jsonFiles.put(fileName,fileContent);
    }

    public InstanceCreator(List<String> list) {
    }

    public <T> T getBean( String name, Class<T> tClass) throws IOException {

        return convertJson(jsonFiles.get(name),tClass) ;
    }

    private String getFileName(String s) {
        String[] pathContent =  s.split("/");
        return pathContent[pathContent.length - 1].replace(".json","");
    }
}
