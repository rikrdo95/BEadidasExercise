package com.adidas.serenitySteps;

import com.adidas.serenitySteps.steps;
import com.adidas.utils.utils;

import java.io.File;
import java.util.List;
import net.thucydides.core.annotations.Step;
import org.hamcrest.MatcherAssert.assertThat; 
import org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;

public class steps {

    private static RequestSpecification request;
    private static Response response;
 
    private static String baseUrl;
    private static String getEndpoint;
    private static String postEndpoint;
    private static String updateEndpoint;
    private static String deleteEndpoint;
 
    private static int lastPetCreatedId;

    @Step
    public static void iWantToTestNAMEAPI(String api) {
      String apiPrefix = "";
      switch(api){
        case "PetStore":
          apiPrefix = api.toUpperCase()+"_";
      }
      baseUrl = utils.getProjectProperties(apiPrefix+"BASE_URL");
      getEndpoint = utils.getProjectProperties(apiPrefix+"GET_ENDPOINT");
      postEndpoint = utils.getProjectProperties(apiPrefix+"POST_ENDPOINT");
      updateEndpoint = utils.getProjectProperties(apiPrefix+"PUT_ENDPOINT");
      deleteEndpoint = utils.getProjectProperties(apiPrefix+"DELETE_ENDPOINT");

      RestAssured.baseURI = baseUrl;
      request = SerenityRest.given().contentType(ContentType.JSON);
    }

    @Step
    public static void iRetrieveAllTheSTATUSPets(String status) {
        response = request.queryParam("status", status ).get(getEndpoint);

        assertThat(response.statusCode(), is(200));

        //Lista con todos los campos "status" de la respuesta
        List<String> statusListResponse = response.jsonPath().getList("status");
        boolean differentStatusFlag = false;
        for(String element : statusListResponse){
            differentStatusFlag = element.equals("available") ? false : true;
        }

        assertThat(differentStatusFlag, is(false));
    }

    @Step
    public static void iPostANewSTATUSPet(String status) {
    }

    @Step
    public static void iUpdateThePetsStatusToSTATUS(String status) {
    }

    @Step
    public static void iDeleteThePetID(String id) {
    }

  }
