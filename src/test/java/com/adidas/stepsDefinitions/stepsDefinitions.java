package com.adidas.stepsDefinitions;

import com.adidas.serenitySteps.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.Given;

  public class stepsDefinitions {

    @Given("^I want to test \"([^\"]*)\" API$")
    public void iWantToTestNAMEAPI(String api) {
      steps.iWantToTestNAMEAPI(api);
    }

    @Given("^I retrieve all the pets with status \"([^\"]*)\"$")
    public void iRetrieveAllThePetsWithStatusSTATUS(String status){
      steps.iRetrieveAllThePetsWithStatusSTATUS(status);
    }

    @And("^I post a new \"([^\"]*)\" pet$")
    public void iPostANewSTATUSPet(String status) {
      steps.iPostANewSTATUSPet(status);
    }
    @Then("^I update the status of the pet \"([^\"]*)\" to \"([^\"]*)\"$")
    public void iUpdateTheStatusOfThePetIDToSTATUS(String id, String status) {
      steps.iUpdateTheStatusOfThePetIDToSTATUS(id, status);
    }

    @And("^I delete the pet \"([^\"]*)\"$")
    public void iDeleteThePetID(String id) {
      steps.iDeleteThePetID(id);
    }

 }	  
