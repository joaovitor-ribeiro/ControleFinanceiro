package controlefinanceiroservicos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "controlefinanceiroservicos")
@EntityScan(basePackages = "controlefinanceiroservicos.model")
public class ControleFinanceiroServicosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControleFinanceiroServicosApplication.class, args);
	}

}