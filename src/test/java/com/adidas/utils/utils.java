package com.adidas.utils;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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

    public static String getJSON(String jsonName, Map<String, String> values){
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("src/test/resources/requests/"+jsonName+".json"));

            JSONObject json = (JSONObject) obj;
            for(Map.Entry<String, String> entry : values.entrySet()){
                json.put(entry.getKey(), entry.getValue());
            }

            return json.toJSONString();
        } catch (Exception e){
            System.out.println(e);
            return "Error al leer/escribir el archivo json "+jsonName+".json";
        }
    }
}
