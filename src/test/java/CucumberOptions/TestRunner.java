package CucumberOptions;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
@CucumberOptions(
        features = "src/test/java/resources/features",
        glue="stepDefinations"
)
public class TestRunner extends AbstractTestNGCucumberTests { }
