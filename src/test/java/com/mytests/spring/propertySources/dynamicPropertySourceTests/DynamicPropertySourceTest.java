package com.mytests.spring.propertySources.dynamicPropertySourceTests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "/beans.xml")
public class DynamicPropertySourceTest {

    private static final MyContainer provider = new MyContainer();

    @DynamicPropertySource  // no validation for the method signature - https://youtrack.jetbrains.com/issue/IDEA-272357
    static void messProperties(DynamicPropertyRegistry registry) {
        registry.add("prop1", provider::firstProperty);
        registry.add("prop2", provider::secondProperty);
    }
    @Value("${prop1}")  // not resolved - https://youtrack.jetbrains.com/issue/IDEA-272360
    String mess1;
    @Value("${prop2}") // not resolved - https://youtrack.jetbrains.com/issue/IDEA-272360
    String mess2;

    @Test
    public void testMess1(){
        Assert.assertEquals(mess1,"hello");
    }
    @Test
    public void testMess2(){
        Assert.assertEquals(mess2,"bye");
    }

    private static class MyContainer {
        public String firstProperty() {
            return "hello";
        }

        public String secondProperty() {
            return "bye";
        }
    }
}
