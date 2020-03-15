package pt.jmnpedrosa.samples.springboot.restful.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pt.jmnpedrosa.samples.springboot.restful.error.UserAlreadyExistsException;
import pt.jmnpedrosa.samples.springboot.restful.error.UserException;
import pt.jmnpedrosa.samples.springboot.restful.error.UserNotFoundException;
import pt.jmnpedrosa.samples.springboot.restful.model.User;

@ExtendWith(SpringExtension.class)
public class DefaultUserServiceTest {

  @Mock
  private Set<User> userSet;

  @InjectMocks
  private UserService userService = new DefaultUserService();

  @Test
  public void test_getAllUsers() {
    User user1 = new User("userName1");
    User user2 = new User("userName2");
    when(userSet.toArray()).thenReturn(new Object[]{user1, user2});
    List<User> result = userService.getAllUsers();
    assertNotNull(result);
    assertEquals(2, result.size());
  }

  @Test
  public void test_getUser_OK() throws Exception {
    User user = new User("userName");
    user.setEmail("email");
    user.setFirstName("firstName");
    user.setLastName("lastName");
    user.setTelephone("telephone");
    user.setAddress("address");
    user.setCountry("country");
    when(userSet.stream()).then(i -> Stream.of(user));

    User result = userService.getUser("userName");
    assertEquals(user, result);
  }

  @Test
  public void test_getUser_MissingInput() {
    assertThrows(UserException.class, () ->
        userService.getUser(null));
  }

  @Test
  public void test_getUser_UserNotFound() {
    assertThrows(UserNotFoundException.class, () ->
        userService.getUser("userName"));
  }

  @Test
  public void test_createUser_OK() throws Exception {
    User user = new User("userName");
    user.setEmail("email@email.com");
    user.setFirstName("firstName");
    user.setLastName("lastName");
    user.setTelephone("123456789");
    user.setAddress("address");
    user.setCountry("country");

    User result = userService.createUser(user);
    assertEquals(user, result);
  }

  @Test
  public void test_createUser_AlreadyExists() {
    User user = new User("userName");
    user.setEmail("email@email.com");
    user.setFirstName("firstName");
    user.setLastName("lastName");
    user.setTelephone("123456789");
    user.setAddress("address");
    user.setCountry("country");
    when(userSet.stream()).then(i -> Stream.of(user));

    assertThrows(UserAlreadyExistsException.class, () ->
        userService.createUser(user));
  }

  @Test
  public void test_updateUser_OK() throws Exception {
    User user = new User("userName");
    user.setEmail("email@email.com");
    user.setFirstName("firstName");
    user.setLastName("lastName");
    user.setTelephone("123456789");
    user.setAddress("address");
    user.setCountry("country");
    when(userSet.stream()).then(i -> Stream.of(user));

    User result = userService.updateUser(user);
    assertEquals(user, result);
  }

  @Test
  public void test_updateUser_UserNotFound() {
    User user = new User("userName");
    user.setEmail("email@email.com");
    user.setFirstName("firstName");
    user.setLastName("lastName");
    user.setTelephone("123456789");
    user.setAddress("address");
    user.setCountry("country");

    assertThrows(UserNotFoundException.class, () ->
        userService.updateUser(user));
  }

  @Test
  public void test_deleteUser_OK() throws Exception {
    User user = new User("userName");
    user.setEmail("email@email.com");
    user.setFirstName("firstName");
    user.setLastName("lastName");
    user.setTelephone("123456789");
    user.setAddress("address");
    user.setCountry("country");
    when(userSet.stream()).then(i -> Stream.of(user));

    User result = userService.deleteUser("userName");
    assertEquals(user, result);
  }

  @Test
  public void test_deleteUser_UserNotFound() {
    assertThrows(UserNotFoundException.class, () ->
        userService.deleteUser("userName"));
  }

}
