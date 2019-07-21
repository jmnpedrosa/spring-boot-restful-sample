package pt.jmnpedrosa.samples.springboot.restful.service;

import java.util.List;
import pt.jmnpedrosa.samples.springboot.restful.error.UserException;
import pt.jmnpedrosa.samples.springboot.restful.model.User;

public interface UserService {

  /**
   * Returns all existing users.
   *
   * @return a list of all User objects
   */
  List<User> getAllUsers();

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

  /**
   * Updates an existing user, by replacing it with an entire
   * new User object.
   *
   * @param user the User to update
   * @return the updated User object.
   * @throws UserException if the user does not exist or could not be updated.
   */
  User updateUser(User user) throws UserException;

  /**
   * Deletes an existing user, returning the deleted user.
   *
   * @param userName the userName of the user to delete.
   * @return the User that has been deleted.
   * @throws UserException if there is an error deleting the user, or it does not exist.
   */
  User deleteUser(String userName) throws UserException;

}
