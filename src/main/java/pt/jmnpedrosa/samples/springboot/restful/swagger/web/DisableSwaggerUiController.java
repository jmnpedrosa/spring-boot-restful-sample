package pt.jmnpedrosa.samples.springboot.restful.swagger.web;

import javax.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class will disable and hide from the user the Swagger UI page unless
 * a flag is declared in properties to enable it.
 */
@RestController
@ConditionalOnProperty(name = "swagger.enabled", havingValue = "false")
public class DisableSwaggerUiController {

  @GetMapping("swagger-ui.html")
  public void getSwagger(HttpServletResponse httpResponse) {
    httpResponse.setStatus(HttpStatus.NOT_FOUND.value());
  }

}
