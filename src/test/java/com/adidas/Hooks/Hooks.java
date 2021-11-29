package com.adidas.hooks;

import com.adidas.serenitySteps.steps;
import com.adidas.utils.utils;

import cucumber.api.java.Before;
import cucumber.api.java.After;
import io.restassured.response.Response;

// Those methods are triggered before and after the scenarios. The scope of those methods can be
// adjusted to specific scenarios with the tags like @PetStore
public class Hooks {
	@Before("@PetStore")
	public void beforeScenario() {
		System.out.println("--------------------START OF SCENARIO--------------------");
		System.out.println("--------------------START OF BEFORE HOOK--------------------");

		// In this case we don't need to do anythig before the scenario, but can be useful
		// to set data in order to run a test if needs it. The scope of those methods can be
		// adjusted to specific scenarios with the tags @

		System.out.println("--------------------END OF BEFORE HOOK--------------------");

	}

	@After("@PetStore")
	public void afterScenario() {
		System.out.println("--------------------START AFTER HOOK--------------------");

		// We make sure to don't leave garbage stored if the test fails
		Response response = utils.getRequest().delete(steps.deleteEndpoint+ steps.lastPetCreatedId);
		System.out.println(
				response.statusCode() == 200 ? "Element deleted successfully" : "Element could not be deleted");

		System.out.println("--------------------END OF AFTER HOOK--------------------");
		System.out.println("--------------------END OF SCENARIO--------------------");
	}
}