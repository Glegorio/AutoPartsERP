package com.autoparts.erp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @Configuration indica ao Spring que esta classe contém configurações do sistema
@Configuration
public class OpenAPIConfig {

    // @Bean faz com que o Spring execute este método e gerencie o objeto retornado
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("ERP Auto Peças - API REST")
                        .version("1.0")
                        .description("Backend do sistema de gerenciamento para lojas de peças automotivas.")
                        .contact(new Contact()
                                .name("Suporte ERP Auto Peças")
                                .email("suporte@autopecas.com")));
    }
}