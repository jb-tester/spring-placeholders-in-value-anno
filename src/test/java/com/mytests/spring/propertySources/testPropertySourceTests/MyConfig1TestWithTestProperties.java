package com.mytests.spring.propertySources.testPropertySourceTests;

import com.mytests.spring.propertySources.components.MyCompo1;
import com.mytests.spring.propertySources.configs.MyConfig1;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MyConfig1.class})
@TestPropertySource("/testMyConfig1.properties")
public class MyConfig1TestWithTestProperties {


    @Autowired
    private MyCompo1 myCompo1;

    // https://youtrack.jetbrains.com/issue/IDEA-272397
    // placeholders are resolved  to properties from the configuration class's propertySource,
    // but the ones specified in the @TestPropertySource should be used
    @Value("${my.config1.str1}") String str1;
    @Value("#{'${my.config1.str2}'}") String str2;
    @Value("${my.config1.str3}") String str3;
    @Value("${my.config1.str4}") String str4;

    @Test
    public void testPropertySources(){
        String stringsFromValueAnnotations = str1+" "+str2+" "+str3+" "+str4;
        Assert.assertEquals(stringsFromValueAnnotations,"test mycompo1 str1 test mycompo1 str2 test mycompo1 str3 test mycompo1 str4");
    }
    @Test
    public void getMyCompo1Strings() {
        Assert.assertEquals(myCompo1.getMyCompo1Strings(),"test mycompo1 str1 test mycompo1 str2");
    }
    @Test
    public void testSamePropertiesAreUsed(){
        String stringsFromValueAnnotations = str1+" "+str2;
        Assert.assertEquals(myCompo1.getMyCompo1Strings(),stringsFromValueAnnotations);
    }
}