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

  // I initialize all the variables needed to test the specified API
  @Step
  public static void iWantToTestNAMEAPI(String api) {
    String apiPrefix = "";
    switch (api) {
      case "PetStore":
        apiPrefix = api.toUpperCase() + "_";
    }
    baseUrl = utils.getProjectProperty(apiPrefix + "BASE_URL");
    getEndpoint = utils.getProjectProperty(apiPrefix + "GET_ENDPOINT");
    getByIdEndpoint = utils.getProjectProperty(apiPrefix + "GET_BY_ID_ENDPOINT");
    postEndpoint = utils.getProjectProperty(apiPrefix + "POST_ENDPOINT");
    updateEndpoint = utils.getProjectProperty(apiPrefix + "PUT_ENDPOINT");
    deleteEndpoint = utils.getProjectProperty(apiPrefix + "DELETE_ENDPOINT");

    RestAssured.baseURI = baseUrl;
  }

  @Step
  public static void iRetrieveAllThePetsWithStatusSTATUS(String status) {
    response = utils.getRequest()
        .queryParam("status", status)
        .get(getEndpoint);

    assertThat(response.statusCode(), is(200));

    // List with all "status" fields of the response and check that all of them are
    // the status expected
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
    utils.wait(WAIT_MILIS);
    response = utils.getRequest().get(getByIdEndpoint + lastPetCreatedId);

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
    utils.wait(WAIT_MILIS);
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
    utils.wait(WAIT_MILIS);
    response = utils.getRequest().get(getByIdEndpoint + id);

    assertThat(response.statusCode(), is(404));
  }

}
