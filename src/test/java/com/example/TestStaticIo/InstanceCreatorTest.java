package com.example.TestStaticIo;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class InstanceCreatorTest {

    InstanceCreator creator;

    @Test
    public void createBeanByPathConstructor() throws IOException {

        creator = new InstanceCreator("json/modelOne.json");
        assertTestModelOne(creator.getBean("modelOne", JsonModelContainer.ModelOne.class));
    }

    @Test
    public void createBeanByConstructorWithListFileTest() throws IOException {

        List<String> list = new ArrayList();
        list.add("json/modelOne.json");
        list.add("json/modelTwo.json");

        creator = new InstanceCreator(list);

        assertTestModelOne(creator.getBean("modelOne", JsonModelContainer.ModelOne.class));
        assertTestModelTwo(creator.getBean("modelTwo", JsonModelContainer.ModelTwo.class));
    }
    @Test
    public void createBeanByConstructorBuilder() throws IOException {

        creator =  InstanceCreator
                .instanceBuilder()
                .add("json/modelOne.json")
                .add("json/modelTwo.json")
                .build();

        assertTestModelOne(creator.getBean("modelOne", JsonModelContainer.ModelOne.class));
        assertTestModelTwo(creator.getBean("modelTwo", JsonModelContainer.ModelTwo.class));
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
