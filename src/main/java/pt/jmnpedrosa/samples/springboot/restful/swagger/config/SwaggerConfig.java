package pt.jmnpedrosa.samples.springboot.restful.swagger.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * This class configures Swagger to show all available endpoints inside our
 * base package and also to display a custom documentation title, description
 * and the current Maven version as present in POM.
 *
 * The class will be disabled disabled unless
 * a flag is declared in properties to enable Swagger.
 */
@Configuration
@ConditionalOnProperty(name = "swagger.enabled")
public class SwaggerConfig {

  @Value("${application.version}")
  private String applicationVersion;

  @Bean
  public Docket api() {
    //@formatter:off
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(metaData())
        .select()
        .apis(RequestHandlerSelectors.basePackage("pt.jmnpedrosa.samples.springboot.restful.web"))
        .paths(PathSelectors.any())
        .build();
    //@formatter:on
  }

  private ApiInfo metaData() {
    return new ApiInfoBuilder()
        .title("Spring Boot RESTful Sample")
        .description("A demo of a RESTful service for managing \"Users\" via CRUD operations.")
        .version(applicationVersion)
        .build();
  }

}
