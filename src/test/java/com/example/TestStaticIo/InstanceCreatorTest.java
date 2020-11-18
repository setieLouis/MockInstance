package com.example.TestStaticIo;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InstanceCreatorTest {

    InstanceCreator creator;

    @Test
    public void createBeanByPathConstructor(){

            creator = new InstanceCreator("json/TestModel.json");

            TestModel model = creator.getBean("TestModel",TestModel.class);
            assertThat(model).isNotNull();
            assertThat(model.getAttr1()).isEqualTo("value1");
            assertThat(model.getAttr2()).isEqualTo("value2");
            assertThat(model.getAttr3()).isEqualTo("value3");
    }

}
