package src.test.java.com.adidas.testRunner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;


@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
		features = "src/test/resources/features/petstore.feature", 
		glue =  "steps" )
public class TestRunner {

}