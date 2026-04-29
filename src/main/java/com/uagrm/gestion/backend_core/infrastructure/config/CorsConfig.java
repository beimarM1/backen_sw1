package com.uagrm.gestion.backend_core.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Configuración CORS para permitir peticiones desde el frontend Angular
 * (localhost:4200).
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();

        // 1. IMPORTANTE: No uses "*" si allowCredentials es true.
        // Agrega explícitamente tu URL de Netlify y la de localhost para pruebas.
        config.addAllowedOrigin("https://enterprise-diagrammer.netlify.app");
        config.addAllowedOrigin("http://localhost:4200");

        // 2. Permitir todos los métodos y headers de forma limpia
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");

        // 3. Esto es lo que obliga a que los Origins sean específicos
        config.setAllowCredentials(true);

        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}