package cucumber.Options;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/features",glue={"stepdefinition"},tags="@DeletePlace")
public class TestRunner {
	

}

//To run tags using maven
//mvn test -Dcucumber.options = "--tags @AddPlace"