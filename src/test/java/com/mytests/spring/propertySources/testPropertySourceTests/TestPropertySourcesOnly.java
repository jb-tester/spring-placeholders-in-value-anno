package com.mytests.spring.propertySources.testPropertySourceTests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration()
@TestPropertySource({"/testProperties.properties", "/extraTestProperties.properties"})
public class TestPropertySourcesOnly {

    // https://youtrack.jetbrains.com/issue/IDEA-272397
    // properties are not resolved, though the property files are resolved in the @TestPropertySource annotation:
    @Value("${my.test.props.str1}") String str1;
    @Value("#{'${my.test.props.str2}'}") String str2;
    @Value("${my.test.props.str4}") String str3;

    //@Value("#{${my.config1.str3}}") String str4; // is resolved, but this is incorrect:
                                                 // 1. all project resources are scanned instead of the specified ones
                                                 // 2. this SpEL syntax means that there should be some field or property with name == property value in the bean context

    @Autowired String someStr;

    @Test
    public void testPropertySources(){
        String stringsFromValueAnnotations = str1+" "+str2+" "+str3;
        Assert.assertEquals(stringsFromValueAnnotations,someStr);
    }
    @Configuration
    static class TestSourcesOnlyConfig{

        @Bean
        public String someStr() {
            return "foo bar buzz";
        }
    }
}
