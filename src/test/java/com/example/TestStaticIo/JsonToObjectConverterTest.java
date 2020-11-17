package com.example.TestStaticIo;

import lombok.Data;
import org.junit.Before;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;


public class JsonToObjectConverterTest {

    JsonToObjectConverter jsonToObjectConverter;

    @Before
    public void setup(){

        jsonToObjectConverter = new JsonToObjectConverter();
    }

    @Test
    public void readAndConvertTest() throws IOException {

        TestModel model = jsonToObjectConverter.convert("json/TestMode.json", TestModel.class);

        assertThat(model.attr1).isEqualTo("value1");
        assertThat(model.attr2).isEqualTo("value2");
        assertThat(model.attr3).isEqualTo("value3");
    }

    @Data
    static class TestModel{
        private String attr1;
        private String attr2;
        private String attr3;
    }
}
