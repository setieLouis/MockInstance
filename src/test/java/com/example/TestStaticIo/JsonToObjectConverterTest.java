package com.example.TestStaticIo;


import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonToObjectConverterTest {

    JsonToObjectConverter jsonToObjectConverter;

    @BeforeEach
    public void setup(){

        jsonToObjectConverter = new JsonToObjectConverter();
    }

    @Test
    public void readAndConvertTest(){

        TestModel model = jsonToObjectConverter.convert("path", TestModel.class);

        assertThat(model.attr1).isEqualTo("attr1");
        assertThat(model.attr2).isEqualTo("attr2");
        assertThat(model.attr3).isEqualTo("attr2");
    }




    @Data
    static class TestModel{
        private String attr1;
        private String attr2;
        private String attr3;
    }

}
