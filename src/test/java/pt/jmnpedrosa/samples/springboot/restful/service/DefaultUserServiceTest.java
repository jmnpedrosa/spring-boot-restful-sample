package pt.jmnpedrosa.samples.springboot.restful.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Set;
import java.util.stream.Stream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pt.jmnpedrosa.samples.springboot.restful.error.UserAlreadyExistsException;
import pt.jmnpedrosa.samples.springboot.restful.error.UserException;
import pt.jmnpedrosa.samples.springboot.restful.error.UserNotFoundException;
import pt.jmnpedrosa.samples.springboot.restful.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
public class DefaultUserServiceTest {

  @Mock
  private Set<User> userSet;

  @InjectMocks
  private UserService userService = new DefaultUserService();

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

  @Test(expected = UserException.class)
  public void test_getUser_MissingInput() throws Exception {
    userService.getUser(null);
  }

  @Test(expected = UserNotFoundException.class)
  public void test_getUser_UserNotFound() throws Exception {
    userService.getUser("userName");
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

  @Test(expected = UserAlreadyExistsException.class)
  public void test_createUser_AlreadyExists() throws Exception {
    User user = new User("userName");
    user.setEmail("email@email.com");
    user.setFirstName("firstName");
    user.setLastName("lastName");
    user.setTelephone("123456789");
    user.setAddress("address");
    user.setCountry("country");
    when(userSet.stream()).then(i -> Stream.of(user));

    userService.createUser(user);
  }
}
