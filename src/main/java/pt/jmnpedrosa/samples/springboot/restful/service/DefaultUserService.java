package pt.jmnpedrosa.samples.springboot.restful.service;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.jmnpedrosa.samples.springboot.restful.error.UserException;
import pt.jmnpedrosa.samples.springboot.restful.error.UserNotFoundException;
import pt.jmnpedrosa.samples.springboot.restful.model.User;

@Service
public class DefaultUserService implements UserService {

  @Autowired
  private Set<User> userSet;

  @Override
  public User getUser(String userName) throws UserException {
    if (userName == null) {
      throw new UserException("Missing input.");
    }

    User user = userSet.stream()
        .filter(u -> u.getUserName().equals(userName))
        .findFirst().orElse(null);

    if (user == null) {
      throw new UserNotFoundException();
    }
    return user;
  }
}
