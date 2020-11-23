package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/features/contato.feature",
		glue = "steps",
		snippets = SnippetType.CAMELCASE,
		tags = "@envioSucesso",
		plugin = "pretty",
		monochrome = true
		)
public class ContatoRunner {

}
