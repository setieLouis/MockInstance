package com.example.TestStaticIo;


import java.io.IOException;
import java.util.*;

import static com.example.TestStaticIo.JsonToObjectConverter.*;

public class InstanceCreator {

    Map<String , String> jsonFiles;

    InstanceCreator(){
        jsonFiles = new HashMap<>();
    }

    public InstanceCreator(String s) throws IOException {
        this();
        create(s);
    }

    public InstanceCreator(List<String> list) throws IOException {
        this();
        for (String fileName : list)
            create(fileName);
    }

    public <T> T getBean( String name, Class<T> tClass) throws IOException {
        return convertJson(jsonFiles.get(name),tClass) ;
    }

    private void create(String s) throws IOException {
        String fileContent =  retrieveJsonFile(s);
        String fileName = getFileName(s);
        jsonFiles.put(fileName,fileContent);
    }


    public static FileBuilder instanceBuilderByFile() {
        return new FileBuilder();
    }

    public static class FileBuilder{
        List<String> paths;

        FileBuilder(){
            paths = new ArrayList();
        }

        public FileBuilder add(String path) {
            paths.add(path);
            return this;
        }

        public InstanceCreator build() throws IOException {
            return new InstanceCreator(paths);
        }
    }

    private String getFileName(String s) {
        String[] pathContent =  s.split("/");
        return pathContent[pathContent.length - 1].replace(".json","");
    }

}
