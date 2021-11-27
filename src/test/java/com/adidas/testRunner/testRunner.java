package com.adidas.testRunner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features/petstore.feature", glue = { "com.adidas.stepsDefinitions",
		"com.adidas.hooks" }, plugin = { "pretty" })
public class TestRunner {

}