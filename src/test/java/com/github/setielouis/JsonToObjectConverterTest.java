package com.github.setielouis;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


public class JsonToObjectConverterTest {

    public static final String JSON_MODEL_ONE_JSON = "json/modelOne.json";
    public static final String WRONG_PATH = "wrongPath";
    public static final String NOT_FOUND_EXCEPTION_MESSAGE = "class path resource [wrongPath] cannot be resolved to absolute file path because it does not exist";

    JsonToObjectConverter jsonToObjectConverter;

    @Test
    public void notFoundFile_test() {

        assertThatExceptionOfType(FileNotFoundException.class)
                .isThrownBy(() ->
                        JsonToObjectConverter.convert(WRONG_PATH, JsonModelContainer.ModelOne.class)
                )
                .withMessage(NOT_FOUND_EXCEPTION_MESSAGE);
    }

    @Test
    public void readAndConvesrtTest() throws IOException {

        JsonModelContainer.ModelOne model = JsonToObjectConverter.convert(JSON_MODEL_ONE_JSON, JsonModelContainer.ModelOne.class);
        assertModelone(model);
    }

    @Test
    public void retrieveJsonFile_test() throws IOException {

        String json = JsonToObjectConverter.retrieveJsonFile(JSON_MODEL_ONE_JSON);
        assertModelone(JsonToObjectConverter.convertJson(json, JsonModelContainer.ModelOne.class));
    }

    private void assertModelone(JsonModelContainer.ModelOne model) {
        assertThat(model.getAttr1()).isEqualTo("value1");
        assertThat(model.getAttr2()).isEqualTo("value2");
        assertThat(model.getAttr3()).isEqualTo("value3");
    }

}
