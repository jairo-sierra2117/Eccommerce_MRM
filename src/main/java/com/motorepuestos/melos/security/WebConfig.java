package com.motorepuestos.melos.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // modificar cuando se despliegue la api y el frontend
                //.allowedOrigins("http://127.0.0.1:5500") //aqui va la URL de la pagina cuando se despliegue
                //.allowedOrigins("http://127.0.0.1:5501") //aqui va la URL de la pagina cuando se despliegue
                //.allowedOrigins("http://127.0.0.1:5502") //aqui va la URL de la pagina cuando se despliegue
                .allowedOrigins("https://jairo-sierra2117.github.io") //aqui va la URL de la pagina cuando se despliegue
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }
}
