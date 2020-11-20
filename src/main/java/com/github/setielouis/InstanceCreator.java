package com.github.setielouis;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InstanceCreator {

    Map<String , String> jsonFiles;

    private InstanceCreator() {
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
     * @return
     */
    public static String byClassTypeBuilder() {
        // TODO not yet implemented
        return null;
    }

    public <T> T getBean(String name, Class<T> tClass) throws IOException {
        return JsonToObjectConverter.convertJson(jsonFiles.get(name), tClass);
    }

    private void create(String s) throws IOException {
        String fileContent = JsonToObjectConverter.retrieveJsonFile(s);
        String fileName = getFileName(s);
        jsonFiles.put(fileName, fileContent);
    }


    private String getFileName(String s) {
        String[] pathContent = s.split("/");
        return pathContent[pathContent.length - 1].replace(".json", "");
    }

    public static class FileBuilder {
        List<String> paths;

        FileBuilder() {
            paths = new ArrayList<>();
        }

        /**
         * @param path
         * @return
         */
        public FileBuilder add(String path) {
            paths.add(path);
            return this;
        }

        /**
         * @return
         * @throws IOException
         */
        public InstanceCreator build() throws IOException {
            return new InstanceCreator(paths);
        }

        public FileBuilder addAll(List<String> list) {
            paths.addAll(list);
            return this;
        }
    }
}
