package com.adidas.utils;

import java.io.FileReader;
import java.util.Properties;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;

public class utils {
    public static String getProjectProperties(String key) {
        String value = "";
        try{
            Properties prop = new Properties();
			FileReader file = new FileReader("project.properties");
            prop.load(file);
            value = prop.getProperty(key);
        } catch (Exception e){
            System.out.println("Unable to read project properties file");
        }
        return value;
    }

    public static RequestSpecification getRequest(){
        return SerenityRest.given().contentType(ContentType.JSON);
    }
}
