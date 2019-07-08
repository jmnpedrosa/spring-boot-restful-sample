package pt.jmnpedrosa.samples.springboot.restful.service;

import pt.jmnpedrosa.samples.springboot.restful.error.UserException;
import pt.jmnpedrosa.samples.springboot.restful.model.User;

public interface UserService {

  /**
   * Finds and returns a User object by its userName.
   *
   * @param userName the userName of the user to find.
   * @return the User object.
   * @throws UserException if there is an error searching for the user.
   */
  User getUser(String userName) throws UserException;

  /**
   * Creates a new User if it does not exist yet.
   *
   * @param user the User to create.
   * @return the created User.
   * @throws UserException if the user already exists or there is an error in insertion.
   */
  User createUser(User user) throws UserException;
}
