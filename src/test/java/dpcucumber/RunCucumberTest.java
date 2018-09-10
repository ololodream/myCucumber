package dpcucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
//@CucumberOptions(plugin = {"io.cucumber.pro.JsonReporter:default"})
//@CucumberOptions(plugin = {"pretty"})
@CucumberOptions(plugin = {"pretty", "json:target/cucumber-reports/Cucumber.json"})

public class RunCucumberTest {
}