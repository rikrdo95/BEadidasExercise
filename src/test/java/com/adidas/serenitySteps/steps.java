package com.adidas.serenitySteps;

import com.adidas.serenitySteps.steps;
import com.adidas.utils.utils;

import static org.mockito.ArgumentMatchers.isA;

import java.io.File;
import java.util.List;
import net.thucydides.core.annotations.Step;
import static org.hamcrest.MatcherAssert.*; 
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class steps {

    private static Response response;
 
    private static String baseUrl;
    private static String getEndpoint;
    private static String getByIdEndpoint;
    private static String postEndpoint;
    private static String updateEndpoint;
    private static String deleteEndpoint;
 
    private static long lastPetCreatedId;

    @Step
    public static void iWantToTestNAMEAPI(String api) {
      String apiPrefix = "";
      switch(api){
        case "PetStore":
          apiPrefix = api.toUpperCase()+"_";
      }
      baseUrl = utils.getProjectProperties(apiPrefix+"BASE_URL");
      getEndpoint = utils.getProjectProperties(apiPrefix+"GET_ENDPOINT");
      getByIdEndpoint = utils.getProjectProperties(apiPrefix+"GET_BY_ID_ENDPOINT");
      postEndpoint = utils.getProjectProperties(apiPrefix+"POST_ENDPOINT");
      updateEndpoint = utils.getProjectProperties(apiPrefix+"PUT_ENDPOINT");
      deleteEndpoint = utils.getProjectProperties(apiPrefix+"DELETE_ENDPOINT");

      RestAssured.baseURI = baseUrl;
    }

    @Step
    public static void iRetrieveAllTheSTATUSPets(String status) {
        response = utils.getRequest().queryParam("status", status ).get(getEndpoint);

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
        File file = new File("src/test/resources/requests/NewAvailablePet.json");
        response = utils.getRequest().body(file).post(postEndpoint);
        
        assertThat(response.statusCode(), is(200));

        lastPetCreatedId = response.jsonPath().get("id");

        assertThat(response.jsonPath().get("id"), is(lastPetCreatedId));

        // Check that the new pet has properly been created
        response = utils.getRequest().get(getByIdEndpoint+lastPetCreatedId);
        // Sometimes the service doesn't get actualized immediately since the POST, 
        // so adding the wait we double-check. Sometimes it takes even more time
        // than the waited, so we could talk about a time response issue of the server
        if(response.statusCode() == 404){
          wait(5000);
          response = utils.getRequest().get(getByIdEndpoint+lastPetCreatedId);  
        }

        assertThat(response.statusCode(), is(200));
        assertThat(response.jsonPath().get("id"), is(lastPetCreatedId));
        assertThat(response.jsonPath().get("status"), is("available"));
    }

    @Step
    public static void iUpdateThePetsStatusToSTATUS(String status) {
    }

    @Step
    public static void iDeleteThePetID(String id) {
    }

    private static void wait(int milis){
      try{
        Thread.sleep(milis);
      } catch (Exception e){
        System.out.println(e);
      }
    }
  }
