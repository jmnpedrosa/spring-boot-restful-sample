package pt.jmnpedrosa.samples.springboot.restful.web;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.jmnpedrosa.samples.springboot.restful.model.User;

@RestController
@RequestMapping("/users")
public class UsersRestController {

  /**
   * Finds and return a User based on it's username.
   * @param userName the user's name.
   * @return the User for that username.
   */
  @GetMapping(value = "/user/{userName}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public User getUser(@PathVariable String userName) {
    return new User(userName);
  }

}
