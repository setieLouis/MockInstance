package com.example.TestStaticIo;

import lombok.Data;
import org.junit.Before;
import org.junit.Test;


import java.io.FileNotFoundException;
import java.io.IOException;

import static org.assertj.core.api.Assertions.*;


public class JsonToObjectConverterTest {

    JsonToObjectConverter jsonToObjectConverter;



    @Test
    public void readAndConvertTest() throws IOException {

        assertThatExceptionOfType(FileNotFoundException.class)
                .isThrownBy( () ->
                jsonToObjectConverter.convert("wrongPath", TestModel.class)
                 )
                .withMessage("class path resource [wrongPath] cannot be resolved to absolute file path because it does not exist");
    }

    @Test
    public void readAndConvesrtTest() throws IOException {

        TestModel model = jsonToObjectConverter.convert("json/TestMo.json", TestModel.class);

        assertThat(model.getAttr1()).isEqualTo("value1");
        assertThat(model.getAttr2()).isEqualTo("value2");
        assertThat(model.getAttr3()).isEqualTo("value3");
    }

}
