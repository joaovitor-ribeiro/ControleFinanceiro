package controlefinanceiroservicos.config;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableWebMvc
@EnableSwagger2
public class ControleFinanceiroConfig implements WebMvcConfigurer {
	
	@Bean
	public Docket forumApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("controlefinanceiroservicos"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(infosApi())
				.consumes(new HashSet< String >(Arrays.asList( MediaType.APPLICATION_JSON_VALUE )))
				.produces(new HashSet< String >(Arrays.asList( MediaType.APPLICATION_JSON_VALUE )))
				.genericModelSubstitutes( ResponseEntity.class )
				.forCodeGeneration( true );
	}
	
	private ApiInfo infosApi() {
		return new ApiInfoBuilder()
				.title("CONTROLE-FINANCEIRO")
				.description("API controle financeiro")
				.build();
	}

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("*")
            .allowedHeaders("*")
            .allowedMethods("PUT", "DELETE", "GET", "POST", "OPTIONS", "HEAD", "TRACE", "CONNECT");
    }
    
     @Override
     public void addResourceHandlers(ResourceHandlerRegistry registry) {
         registry.addResourceHandler("/resources/**").addResourceLocations("/resources/"); 
         registry.addResourceHandler("/api/swagger-ui.html**").addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
		 registry.addResourceHandler("/api/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		 registry.addResourceHandler("/api/**").addResourceLocations("/api/**");
     }
     
     @Override
     public void addViewControllers(ViewControllerRegistry registry) {
         registry.addRedirectViewController("/api/v2/api-docs", "/v2/api-docs");
         registry.addRedirectViewController("/api/swagger-resources/configuration/ui", "/swagger-resources/configuration/ui");
         registry.addRedirectViewController("/api/swagger-resources/configuration/security", "/swagger-resources/configuration/security");
         registry.addRedirectViewController("/api/swagger-resources", "/swagger-resources");
     }

}