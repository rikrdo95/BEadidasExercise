package com.adidas.stepsDefinitions;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {
	@Before("@PetStore")
	public void beforeScenario(){
		
		System.out.println("--------------------START OF SCENARIO--------------------");
	}
	
	@After("@PetStore")
	public void afterScenario() {
		
		System.out.println("--------------------End OF SCENARIO--------------------");
		
	}

}