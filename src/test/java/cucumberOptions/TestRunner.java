package cucumberOptions;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
//manual import?
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/java/features",
		glue = "stepDefinitions",
		//plugin: part of cucumber reporting
		plugin="json:target/jsonReports/cucumber-report.json",
		//tags for run only one test with the tag
		tags ="@DeletePlace"
		)
//features; if want run specific file use "src/test/java/features.Login.feature", if not just use the line like now, and will find all.features
//glue: write the packageName of stepdefinition.class 
public class TestRunner {
	
}
