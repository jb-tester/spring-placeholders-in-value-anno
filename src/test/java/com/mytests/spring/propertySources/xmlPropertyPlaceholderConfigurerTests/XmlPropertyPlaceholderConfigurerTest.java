package com.mytests.spring.propertySources.xmlPropertyPlaceholderConfigurerTests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "/propertyPlaceholderConfigurerConfig.xml")
public class XmlPropertyPlaceholderConfigurerTest {

    // https://youtrack.jetbrains.com/issue/IDEA-272406
    @Value("${my.xml.prop1}") String str1; // ok
    @Value("#{'${my.xml.prop2}'}") String str2; // property key is shown as not used
    @Value("#{'${my.xml.prop5}' + ' ' + '${my.xml.prop6}'}") String str56; // both keys are shown as unused
    @Autowired
    private String xmlStrings1;
    @Autowired
    private String xmlStrings2;


    @Test
    public void testPropertiesFromValueAnnotations(){
        String stringsFromValueAnnotations = str1+" "+str2;
        Assert.assertEquals(stringsFromValueAnnotations,"prop1 prop2");
    }
    @Test
    public void testPropertiesFromXMLBeans(){
        String stringsFromXmlBeans = xmlStrings1+" "+xmlStrings2;
        Assert.assertEquals(stringsFromXmlBeans,"prop3 prop4");
    }
}
