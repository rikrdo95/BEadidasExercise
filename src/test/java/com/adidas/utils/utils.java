package com.adidas.utils;

import java.io.FileReader;
import java.util.Map;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;

public class utils {

    // Returns the specified property from project.properties
    public static String getProjectProperty(String key) {
        String value = "";
        try {
            Properties prop = new Properties();
            FileReader file = new FileReader("project.properties");
            prop.load(file);
            value = prop.getProperty(key);
        } catch (Exception e) {
            System.out.println("Unable to read project properties file");
        }
        return value;
    }

    public static RequestSpecification getRequest() {
        return SerenityRest.given().contentType(ContentType.JSON);
    }

    // Gets the specified json and replace the given fields with the respective values
    public static String getJSON(String jsonName, Map<String, String> values) {
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("src/test/resources/jsons/" + jsonName + ".json"));

            JSONObject json = (JSONObject) obj;
            for (Map.Entry<String, String> entry : values.entrySet()) {
                json.put(entry.getKey(), entry.getValue());
            }

            return json.toJSONString();
        } catch (Exception e) {
            System.out.println(e);
            return "Error al leer el archivo json " + jsonName + ".json";
        }
    }
    
    // Explicit wait needed to wait the server update the data
    public static void wait(int milis) {
        try {
          Thread.sleep(milis);
        } catch (Exception e) {
          System.out.println(e);
        }
      }
    
}
