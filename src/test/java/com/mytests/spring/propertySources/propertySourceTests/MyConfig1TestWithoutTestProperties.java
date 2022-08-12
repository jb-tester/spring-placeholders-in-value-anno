package com.mytests.spring.propertySources.propertySourceTests;

import com.mytests.spring.propertySources.components.MyCompo1;
import com.mytests.spring.propertySources.configs.MyConfig1;
import com.mytests.spring.propertySources.myAnnotations.Str3;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MyConfig1.class})
public class MyConfig1TestWithoutTestProperties {


    @Autowired
    private MyCompo1 myCompo1;

    // placeholders are resolved correctly to properties from the configuration class's propertySource
    @Value("${my.config1.str1}") String str1;
    @Value("#{'${my.config1.str2}'}") String str2;
    @Str3 String str3;
    @Test
    public void testPropertySources(){
        String stringsFromValueAnnotations = str1+" "+str2+" "+str3;
        Assert.assertEquals(stringsFromValueAnnotations,"mycompo1 str1 mycompo1 str2 mycompo1 str3");
    }
    @Test
    public void getMyCompo1Strings() {
        Assert.assertEquals(myCompo1.getMyCompo1Strings(),"mycompo1 str1 mycompo1 str2 mycompo1 str3");
    }
    @Test
    public void testSamePropertiesAreUsed(){
        String stringsFromValueAnnotations = str1+" "+str2+" "+str3;
        Assert.assertEquals(myCompo1.getMyCompo1Strings(),stringsFromValueAnnotations);
    }
}