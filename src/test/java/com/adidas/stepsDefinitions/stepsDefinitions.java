package com.adidas.stepsDefinitions;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;


  public class stepsDefinitions {

    private int lastPetCcreatedId;

    @Given("^I retrieve all the \"([^\"]*)\" pets$")
    public void iRetrieveAllTheSTATUSPets(String status) {
      System.out.println("ÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇ");
    }

    @And("^I post a new \"([^\"]*)\" pet$")
    public void iPostANewSTATUSPet(String status) {
      throw new PendingException();
    }

    @Then("^I update the pet's status to \"([^\"]*)\"$")
    public void iUpdateThePetsStatusToSTATUS(String status) {
      throw new PendingException();
    }

    @And("^I delete the pet \"([^\"]*)\"$")
    public void iDeleteThePetID(int id) {
      throw new PendingException();
    }

 }	  
