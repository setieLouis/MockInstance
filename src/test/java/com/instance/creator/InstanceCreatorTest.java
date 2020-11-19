package com.instance.creator;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class InstanceCreatorTest {

    public static final String JSON_MODEL_ONE_JSON = "json/modelOne.json";
    public static final String JSON_MODEL_TWO_JSON = "json/modelTwo.json";
    public static final String MODEL_ONE = "modelOne";
    public static final String MODEL_TWO = "modelTwo";

    InstanceCreator creator;

    @Test
    public void createBeanByPathConstructor() throws IOException {

        creator = InstanceCreator.byFile(JSON_MODEL_ONE_JSON);
        assertTestModelOne(creator.getBean(MODEL_ONE, JsonModelContainer.ModelOne.class));
    }

    @Test
    public void createBeanByConstructorWithListFileTest() throws IOException {

        List<String> list = new ArrayList();
        list.add(JSON_MODEL_ONE_JSON);
        list.add(JSON_MODEL_TWO_JSON);

        creator =  InstanceCreator.byFileList(list);

        assertTestModelOne(creator.getBean(MODEL_ONE, JsonModelContainer.ModelOne.class));
        assertTestModelTwo(creator.getBean(MODEL_TWO, JsonModelContainer.ModelTwo.class));
    }

    @Test
    public void fileConstructorBuilder() throws IOException {

        creator = InstanceCreator
                .byFileBuilder()
                .add(JSON_MODEL_ONE_JSON)
                .add(JSON_MODEL_TWO_JSON)
                .build();

        assertTestModelOne(creator.getBean(MODEL_ONE, JsonModelContainer.ModelOne.class));
        assertTestModelTwo(creator.getBean(MODEL_TWO, JsonModelContainer.ModelTwo.class));
    }

    @Test
    public void fileConstructorBuilderList() throws IOException {

        creator = InstanceCreator
                .byFileBuilder()
                .addAll(Collections.singletonList(JSON_MODEL_ONE_JSON))
                .add(JSON_MODEL_TWO_JSON)
                .build();

        assertTestModelOne(creator.getBean(MODEL_ONE, JsonModelContainer.ModelOne.class));
        assertTestModelTwo(creator.getBean(MODEL_TWO, JsonModelContainer.ModelTwo.class));
    }


    private void assertTestModelOne(JsonModelContainer.ModelOne model) {

        assertThat(model).isNotNull();
        assertThat(model.getAttr1()).isEqualTo("value1");
        assertThat(model.getAttr2()).isEqualTo("value2");
        assertThat(model.getAttr3()).isEqualTo("value3");
    }

    private void assertTestModelTwo(JsonModelContainer.ModelTwo model) {

        assertThat(model).isNotNull();
        assertThat(model.getAttr1()).isEqualTo("value1");
        assertThat(model.getAttr2()).isEqualTo("value2");
    }
}
