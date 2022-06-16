package com.generation.comnectar.configuration;

import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;

@Configuration
public class SwaggerConfig {
	@Bean
	public OpenAPI springComnectarOpenAPI() {
		return new OpenAPI()
			.info(new Info()
				.title("Comnectar")
				.description("Projeto Integrador - G4/Turma 52 - Generation Brasil")
				.version("v0.0.1")
			.license(new License()
				.name("Generation Brasil")
				.url("https://brazil.generation.org/"))
			/*.contact(new Contact()
				.name("Andrei Ferreira Lançanova")
				.url("https://github.com/andreiflancanova/")
				.email("andreiflancanova@hotmail.com"))
			.contact(new Contact()
				.name("Isabela das Neves Piana")
				.url("https://github.com/isabelapiana/")
				.email("isabelapiana91@gmail.com"))
			.contact(new Contact()
				.name("Lairton da Silva")
				.url("https://github.com/Lairtondasilva/")
				.email("lairtondasilva07@gmail.com"))
			.contact(new Contact()
				.name("Luan Teixeira Saramago")
				.url("https://github.com/luantss/")
				.email("luan.teixeira.saramago@gmail.com"))
			.contact(new Contact()
				.name("Luciana Rocha Costa")
				.url("https://github.com/lucianarochacosta/")
				.email("costarluciana@gmail.com"))*/
			.contact(new Contact()
				.name("Comnectar. Equipe: Andrei, Isabela, Lairton, Luan, Luciana, Tácio")
				.url("https://github.com/andreiflancanova/Projeto_Integrador_Generation")
				.email("taciosfer@gmail.com")))
			.externalDocs(new ExternalDocumentation()
				.description("GitHub - Repositório do Projeto")
				.url("https://github.com/andreiflancanova/Projeto_Integrador_Generation"));
	}
	@Bean
	public OpenApiCustomiser customerGlobalHeaderOpenApiCustomiser() {
		return openApi -> {
			openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {
				ApiResponses apiResponses = operation.getResponses();
				apiResponses.addApiResponse("200", createApiResponse("Sucesso!"));
				apiResponses.addApiResponse("201", createApiResponse("Objeto Persistido!"));
				apiResponses.addApiResponse("204", createApiResponse("Objeto Excluído!"));
				apiResponses.addApiResponse("400", createApiResponse("Erro na Requisição!"));
				apiResponses.addApiResponse("401", createApiResponse("Acesso Não Autorizado!"));
				apiResponses.addApiResponse("404", createApiResponse("Objeto Não Encontrado!"));
				apiResponses.addApiResponse("500", createApiResponse("Erro na Aplicação!"));
			}));
		};
	}

	private ApiResponse createApiResponse(String message) {

		return new ApiResponse().description(message);
	}
}