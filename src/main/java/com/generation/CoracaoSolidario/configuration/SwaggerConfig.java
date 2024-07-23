package com.generation.CoracaoSolidario.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    OpenAPI springCoracaoSolidarioOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Projeto Integrador - Coração Solidário")
                        .description("Projeto Integrador - Coração Solidário - Generation Brasil")
                        .version("v0.0.1")
                        .license(new License()
                                .name("Generation Brasil")
                                .url("https://brazil.generation.org/"))
                        .contact(new Contact()
                                .name("Wellington Albino, Lucas Carlos, Thiago Lima, Wendy Souza, Camila Vildoso, Caroline Coutinho")
                                .url("https://github.com/Thichi99/ProjetoIntegrador_CoracaoSolidario")
                                .email(
                                        "well.renato@hotmail.com" +
                                                "contato.wendy@hotmail.com" +
                                                "lucassscarlosss54@gmail.com" +
                                                "thiago.plima99@gmail.com" +
                                                "contacaroline.coutinho@gmail.com" +
                                                "camiliun123@gmail.com"
                                )))
                .externalDocs(new ExternalDocumentation()
                        .description("Github")
                        .url("https://github.com/Thichi99/ProjetoIntegrador_CoracaoSolidario"));
    }

    @Bean
    OpenApiCustomizer customerGlobalHeaderOpenApiCustomiser() {
        return openApi -> {
            openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations()
                    .forEach(operation -> {
                        ApiResponses apiResponses = operation.getResponses();

                        apiResponses.addApiResponse("200", createApiResponse("Sucesso!"));
                        apiResponses.addApiResponse("201", createApiResponse("Objeto Persistido!"));
                        apiResponses.addApiResponse("204", createApiResponse("Objeto Excluído!"));
                        apiResponses.addApiResponse("400", createApiResponse("Erro na requisição!"));
                        apiResponses.addApiResponse("401", createApiResponse("Acesso não autorizado!"));
                        apiResponses.addApiResponse("403", createApiResponse("Acesso proibido!"));
                        apiResponses.addApiResponse("404", createApiResponse("Objeto não encontrado!"));
                        apiResponses.addApiResponse("500", createApiResponse("Erro na aplicação!"));
                    }));
        };
    }

    private ApiResponse createApiResponse(String message) {
        return new ApiResponse().description(message);
    }

}
