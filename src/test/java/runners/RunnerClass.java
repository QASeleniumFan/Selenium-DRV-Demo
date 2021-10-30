package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin={"pretty","html:format"},
        features = "Features/name.feature",glue={"C:\\Users\\dmarc\\IdeaProjects\\SeleniumDRVDemo\\src\\test\\java\\stepDefs"})

public class RunnerClass {
}
