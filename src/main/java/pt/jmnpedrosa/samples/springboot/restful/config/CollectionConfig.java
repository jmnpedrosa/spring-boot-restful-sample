package pt.jmnpedrosa.samples.springboot.restful.config;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pt.jmnpedrosa.samples.springboot.restful.model.User;

@Configuration
public class CollectionConfig {

  /**
   * Configures a singleton synchronized HashSet in memory
   * to store our User model instances.
   */
  @Bean
  public Set<User> userSet() {
    return Collections.synchronizedSet(new HashSet<>());
  }

}
