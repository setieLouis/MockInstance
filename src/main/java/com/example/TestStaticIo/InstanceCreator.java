package com.example.TestStaticIo;


import java.io.IOException;
import java.util.*;

import static com.example.TestStaticIo.JsonToObjectConverter.*;

public class InstanceCreator {

    Map<String , String> jsonFiles;

    InstanceCreator(){
        jsonFiles = new HashMap<>();
    }

    private InstanceCreator(String s) throws IOException {
        this();
        create(s);
    }

    private InstanceCreator(List<String> list) throws IOException {
        this();
        for (String fileName : list)
            create(fileName);
    }


    /**
     *
     * @param fileName
     * @return
     * @throws IOException
     */
    public static InstanceCreator byFile(String fileName) throws IOException {
        return new InstanceCreator(fileName);
    }

    /**
     *
     * @param files
     * @return
     * @throws IOException
     */
    public static InstanceCreator byFileList(List<String> files) throws IOException {
        return new InstanceCreator(files);
    }

    /**
     * this class become public when we finish implementation
     * @param clazz
     * @return
     * @throws IOException
     */
    private static void byClazzType(Class clazz) throws IOException {
        // TODO not yet implemented
        return;
    }

    /**
     *
     * @return
     */
    public static FileBuilder byFileBuilder() {
        return new FileBuilder();
    }

    /**
     *
     * @return
     */
    public static String byClassTypeBuilder(){
        // TODO not yet implemented
        return null;
    }



    public <T> T getBean( String name, Class<T> tClass) throws IOException {
        return convertJson(jsonFiles.get(name),tClass) ;
    }

    private void create(String s) throws IOException {
        String fileContent =  retrieveJsonFile(s);
        String fileName = getFileName(s);
        jsonFiles.put(fileName,fileContent);
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

        public FileBuilder addAll(List<String> list) {
            paths.addAll(list);
            return this;
        }
    }

    private String getFileName(String s) {
        String[] pathContent =  s.split("/");
        return pathContent[pathContent.length - 1].replace(".json","");
    }
}
