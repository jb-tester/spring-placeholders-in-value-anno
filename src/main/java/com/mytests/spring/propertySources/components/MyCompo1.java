package com.mytests.spring.propertySources.components;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class MyCompo1 {

    // properties are resolved correctly
    @Value("${my.config1.str1}") String str1;
    @Value("#{'${my.config1.str2}'}") String str2;

    public String getMyCompo1Strings(){
        return str1+" "+str2;
    }
}
