package com.adidas.stepsDefinitions;

import static org.junit.Assert.assertTrue;

import com.adidas.utils.utils;
import com.adidas.serenitySteps.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;
import io.restassured.RestAssured;

  public class stepsDefinitions {

    @Given("^I want to test \"([^\"]*)\" API$")
    public void iWantToTestNAMEAPI(String api) {
      steps.iWantToTestNAMEAPI(api);
    }

    @Given("^I retrieve all the \"([^\"]*)\" pets$")
    public void iRetrieveAllTheSTATUSPets(String status){
      steps.iRetrieveAllTheSTATUSPets(status);
    }

    @And("^I post a new \"([^\"]*)\" pet$")
    public void iPostANewSTATUSPet(String status) {
      steps.iPostANewSTATUSPet(status);
    }

    @Then("^I update the pet's status to \"([^\"]*)\"$")
    public void iUpdateThePetsStatusToSTATUS(String status) {
      steps.iUpdateThePetsStatusToSTATUS(status);
    }

    @And("^I delete the pet \"([^\"]*)\"$")
    public void iDeleteThePetID(String id) {
      steps.iDeleteThePetID(id);
    }

 }	  
