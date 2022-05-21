package controlefinanceiroservicos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication(scanBasePackages = "controlefinanceiroservicos", exclude = SecurityAutoConfiguration.class)
@EntityScan(basePackages = "controlefinanceiroservicos.model")
public class ControleFinanceiroServicosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControleFinanceiroServicosApplication.class, args);
	}
	
	@Bean
    public PasswordEncoder getPassWordEncoder() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        return encoder;
    }

}