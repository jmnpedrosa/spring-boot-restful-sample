package pt.jmnpedrosa.samples.springboot.restful.web;


import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pt.jmnpedrosa.samples.springboot.restful.error.UserException;
import pt.jmnpedrosa.samples.springboot.restful.model.User;
import pt.jmnpedrosa.samples.springboot.restful.service.UserService;

@RestController
@RequestMapping("/")
public class UserRestController {

  @Autowired
  private UserService userService;

  /**
   * Finds and return a User based on it's username.
   *
   * @param userName the user's name.
   * @return the User for that username.
   */
  @GetMapping(value = "/user/{userName}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public User getUser(@PathVariable String userName) throws UserException {
    return userService.getUser(userName);
  }

  /**
   * Creates the specified user.
   *
   * @param user the user to create.
   * @return the the created user.
   */
  @PostMapping(value = "/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public User createUser(@RequestBody @Valid User user) throws UserException {
    return userService.createUser(user);
  }

  /**
   * Updates an existing user, by replacing it with an entire
   * new User object.
   *
   * @param user the user to update.
   * @return the new version of the user.
   */
  @PutMapping(value = "/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public User updateUser(@RequestBody @Valid User user) throws UserException {
    return userService.updateUser(user);
  }

}
