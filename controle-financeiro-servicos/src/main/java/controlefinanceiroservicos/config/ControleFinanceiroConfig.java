package controlefinanceiroservicos.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class ControleFinanceiroConfig implements WebMvcConfigurer{

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");
    }

     @Override
     public void addResourceHandlers(ResourceHandlerRegistry registry) {
         registry.addResourceHandler("/resources/")
             .addResourceLocations("/resources/"); 
     }

}