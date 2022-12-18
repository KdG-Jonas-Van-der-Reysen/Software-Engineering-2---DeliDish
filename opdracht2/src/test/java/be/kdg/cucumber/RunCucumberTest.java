package be.kdg.cucumber;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * @author Jan de Rijke.
 * put the cucumber test code in the package specified in the glue attribute
 */
@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "summary"},
        features = "src/test/resources/features"
        // use this if your test classes are in a different package than this one
//		,glue="be.kdg.mytestpackage"
)
public class RunCucumberTest {
}

