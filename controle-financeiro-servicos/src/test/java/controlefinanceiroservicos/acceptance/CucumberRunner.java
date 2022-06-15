package controlefinanceiroservicos.acceptance;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class) 
@CucumberOptions(features = "classpath:features", tags = "@ganho-cadastro-sucesso")
public class CucumberRunner {

}
