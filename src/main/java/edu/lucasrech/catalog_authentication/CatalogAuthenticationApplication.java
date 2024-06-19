package edu.lucasrech.catalog_authentication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Catálogo de produtos", version = "1", description = "Aplicação de um catálogo de produtos que utiliza autenticação de usuário com JWT."))
public class CatalogAuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatalogAuthenticationApplication.class, args);
	}

}
