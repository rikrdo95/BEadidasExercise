package com.adidas.serenitySteps;

import com.adidas.serenitySteps.steps;
import com.adidas.utils.utils;

import net.thucydides.core.annotations.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import java.util.HashMap;
import java.util.List;

public class steps {

  private static Response response;
  private static HashMap<String, String> bodyFields;

  private static String baseUrl;
  private static String getEndpoint;
  private static String getByIdEndpoint;
  private static String postEndpoint;
  private static String updateEndpoint;
  public static String deleteEndpoint;

  public static Long lastPetCreatedId;
  private static int WAIT_MILIS = 5000;

  @Step
  public static void iWantToTestNAMEAPI(String api) {
    String apiPrefix = "";
    switch (api) {
      case "PetStore":
        apiPrefix = api.toUpperCase() + "_";
    }
    baseUrl = utils.getProjectProperties(apiPrefix + "BASE_URL");
    getEndpoint = utils.getProjectProperties(apiPrefix + "GET_ENDPOINT");
    getByIdEndpoint = utils.getProjectProperties(apiPrefix + "GET_BY_ID_ENDPOINT");
    postEndpoint = utils.getProjectProperties(apiPrefix + "POST_ENDPOINT");
    updateEndpoint = utils.getProjectProperties(apiPrefix + "PUT_ENDPOINT");
    deleteEndpoint = utils.getProjectProperties(apiPrefix + "DELETE_ENDPOINT");

    RestAssured.baseURI = baseUrl;
  }

  @Step
  public static void iRetrieveAllThePetsWithStatusSTATUS(String status) {
    response = utils.getRequest()
        .queryParam("status", status)
        .get(getEndpoint);

    assertThat(response.statusCode(), is(200));

    // Lista con todos los campos "status" de la respuesta
    List<String> statusListResponse = response.jsonPath().getList("status");
    boolean differentStatusFlag = false;
    for (String element : statusListResponse) {
      differentStatusFlag = element.equals("available") ? false : true;
    }

    assertThat(differentStatusFlag, is(false));
  }

  @Step
  public static void iPostANewSTATUSPet(String status) {
    bodyFields = new HashMap<String, String>();
    bodyFields.put("status", status);
    response = utils.getRequest()
        .body(utils.getJSON("pet", bodyFields))
        .post(postEndpoint);

    assertThat(response.statusCode(), is(200));
    assertThat(response.jsonPath().get("status"), is(status));

    lastPetCreatedId = response.jsonPath().get("id");

    // Check that the new pet has properly been created
    response = utils.getRequest().get(getByIdEndpoint + lastPetCreatedId);
    // Sometimes the service doesn't get actualized immediately since the POST,
    // so adding the wait we double-check. Sometimes it takes even more time
    // than the waited, so we could talk about a time response issue of the server
    if (response.statusCode() == 404) {
      wait(WAIT_MILIS);
      response = utils.getRequest().get(getByIdEndpoint + lastPetCreatedId);
    }

    assertThat(response.statusCode(), is(200));
    assertThat(response.jsonPath().get("id"), is(lastPetCreatedId));
    assertThat(response.jsonPath().get("status"), is(status));
  }

  @Step
  public static void iUpdateTheStatusOfThePetIDToSTATUS(String idString, String status) {
    Long id = idString.equals("previously created") ? lastPetCreatedId : Long.parseLong(idString);
    bodyFields = new HashMap<String, String>();
    bodyFields.put("id", String.valueOf(id));
    bodyFields.put("status", status);
    response = utils.getRequest()
        .body(utils.getJSON("pet", bodyFields))
        .put(updateEndpoint);

    assertThat(response.statusCode(), is(200));
    assertThat(response.jsonPath().get("status"), is(status));
    assertThat(response.jsonPath().get("id"), is(id));

    // Check that the new pet has properly been updated
    wait(WAIT_MILIS);
    response = utils.getRequest().get(getByIdEndpoint + id);

    assertThat(response.statusCode(), is(200));
    assertThat(response.jsonPath().get("id"), is(id));
    assertThat(response.jsonPath().get("status"), is(status));
  }

  @Step
  public static void iDeleteThePetID(String idString) {
    Long id = idString.equals("previously created") ? lastPetCreatedId : Long.parseLong(idString);
    response = utils.getRequest()
        .delete(deleteEndpoint + id);

    assertThat(response.statusCode(), is(200));
    assertThat(Long.parseLong(response.jsonPath().get("message")), is(id));

    // Check that the new pet has properly been deleted
    wait(WAIT_MILIS);
    response = utils.getRequest().get(getByIdEndpoint + id);

    assertThat(response.statusCode(), is(404));
  }

  private static void wait(int milis) {
    try {
      Thread.sleep(milis);
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
