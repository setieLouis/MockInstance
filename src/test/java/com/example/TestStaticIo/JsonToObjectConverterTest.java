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
    public void readAndConvertTest() {

        assertThatExceptionOfType(FileNotFoundException.class)
                .isThrownBy( () ->
                jsonToObjectConverter.convert("wrongPath", JsonModelContainer.ModelOne.class)
                 )
                .withMessage("class path resource [wrongPath] cannot be resolved to absolute file path because it does not exist");
    }

    @Test
    public void readAndConvesrtTest() throws IOException {

        JsonModelContainer.ModelOne model = jsonToObjectConverter.convert("json/modelOne.json", JsonModelContainer.ModelOne.class);

        assertThat(model.getAttr1()).isEqualTo("value1");
        assertThat(model.getAttr2()).isEqualTo("value2");
        assertThat(model.getAttr3()).isEqualTo("value3");
    }

}
