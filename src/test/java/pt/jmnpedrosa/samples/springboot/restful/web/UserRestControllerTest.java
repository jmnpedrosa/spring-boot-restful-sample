package pt.jmnpedrosa.samples.springboot.restful.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pt.jmnpedrosa.samples.springboot.restful.error.UserErrorCode;
import pt.jmnpedrosa.samples.springboot.restful.error.UserException;
import pt.jmnpedrosa.samples.springboot.restful.error.UserNotFoundException;
import pt.jmnpedrosa.samples.springboot.restful.model.User;
import pt.jmnpedrosa.samples.springboot.restful.service.DefaultUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(UserRestController.class)
public class UserRestControllerTest {

  private MockMvc webMockMvc;

  @MockBean
  private DefaultUserService defaultUserService;

  @Autowired
  private WebApplicationContext webApplicationContext;

  @Before
  public void setUp() {
    Mockito.reset(defaultUserService);
    webMockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @Test
  public void test_getAllUsers_OK() throws Exception {
    User user1 = new User("userName1");
    user1.setEmail("email1");
    user1.setFirstName("firstName1");
    user1.setLastName("lastName1");
    user1.setTelephone("telephone1");
    user1.setAddress("address1");
    user1.setCountry("country1");

    User user2 = new User("userName2");
    user2.setEmail("email2");
    user2.setFirstName("firstName2");
    user2.setLastName("lastName2");
    user2.setTelephone("telephone2");
    user2.setAddress("address2");
    user2.setCountry("country2");

    when(defaultUserService.getAllUsers()).thenReturn(Arrays.asList(user1, user2));

    webMockMvc.perform(get("/users")
        .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.[0].userName").value(user1.getUserName()))
        .andExpect(jsonPath("$.[0].email").value(user1.getEmail()))
        .andExpect(jsonPath("$.[0].firstName").value(user1.getFirstName()))
        .andExpect(jsonPath("$.[0].lastName").value(user1.getLastName()))
        .andExpect(jsonPath("$.[0].telephone").value(user1.getTelephone()))
        .andExpect(jsonPath("$.[0].address").value(user1.getAddress()))
        .andExpect(jsonPath("$.[0].country").value(user1.getCountry()))
        .andExpect(jsonPath("$.[1].userName").value(user2.getUserName()))
        .andExpect(jsonPath("$.[1].email").value(user2.getEmail()))
        .andExpect(jsonPath("$.[1].firstName").value(user2.getFirstName()))
        .andExpect(jsonPath("$.[1].lastName").value(user2.getLastName()))
        .andExpect(jsonPath("$.[1].telephone").value(user2.getTelephone()))
        .andExpect(jsonPath("$.[1].address").value(user2.getAddress()))
        .andExpect(jsonPath("$.[1].country").value(user2.getCountry()))
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  public void test_getUser_OK() throws Exception {
    User result = new User("userName");
    result.setEmail("email");
    result.setFirstName("firstName");
    result.setLastName("lastName");
    result.setTelephone("telephone");
    result.setAddress("address");
    result.setCountry("country");

    when(defaultUserService.getUser(any())).thenReturn(result);

    webMockMvc.perform(get("/user/userName")
        .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.userName").value(result.getUserName()))
        .andExpect(jsonPath("$.email").value(result.getEmail()))
        .andExpect(jsonPath("$.firstName").value(result.getFirstName()))
        .andExpect(jsonPath("$.lastName").value(result.getLastName()))
        .andExpect(jsonPath("$.telephone").value(result.getTelephone()))
        .andExpect(jsonPath("$.address").value(result.getAddress()))
        .andExpect(jsonPath("$.country").value(result.getCountry()))
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  public void test_getUser_NotFound() throws Exception {
    UserNotFoundException exception = new UserNotFoundException("User not found.");
    when(defaultUserService.getUser(any())).thenThrow(exception);

    webMockMvc.perform(get("/user/userName")
        .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isNotFound())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.error").value(exception.getError().getValue()))
        .andExpect(jsonPath("$.error_description").value(exception.getMessage()))
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  public void test_getUser_UserException() throws Exception {
    UserException exception = new UserException("Unspecified error.");
    when(defaultUserService.getUser(any())).thenThrow(exception);

    webMockMvc.perform(get("/user/userName")
        .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isInternalServerError())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.error").value(exception.getError().getValue()))
        .andExpect(jsonPath("$.error_description").value(exception.getMessage()))
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  public void test_createUser_OK() throws Exception {
    User result = new User("userName");
    result.setEmail("email@email.com");
    result.setFirstName("firstName");
    result.setLastName("lastName");
    result.setTelephone("123456789");
    result.setAddress("address");
    result.setCountry("country");

    when(defaultUserService.createUser(any())).thenReturn(result);

    webMockMvc.perform(post("/user")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(new ObjectMapper().writeValueAsString(result)))
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.userName").value(result.getUserName()))
        .andExpect(jsonPath("$.email").value(result.getEmail()))
        .andExpect(jsonPath("$.firstName").value(result.getFirstName()))
        .andExpect(jsonPath("$.lastName").value(result.getLastName()))
        .andExpect(jsonPath("$.telephone").value(result.getTelephone()))
        .andExpect(jsonPath("$.address").value(result.getAddress()))
        .andExpect(jsonPath("$.country").value(result.getCountry()))
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  public void test_createUser_InvalidInput() throws Exception {
    User result = new User("userName");
    result.setEmail("email");
    result.setFirstName("firstName");
    result.setLastName("lastName");
    result.setTelephone("telephone");
    result.setAddress("address");
    result.setCountry("country");

    webMockMvc.perform(post("/user")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(new ObjectMapper().writeValueAsString(result)))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.error").value(UserErrorCode.INVALID_INPUT.getValue()))
        .andExpect(jsonPath("$.error_description").exists())
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  public void test_updateUser_OK() throws Exception {
    User result = new User("userName");
    result.setEmail("email@email.com");
    result.setFirstName("firstName");
    result.setLastName("lastName");
    result.setTelephone("123456789");
    result.setAddress("address");
    result.setCountry("country");

    when(defaultUserService.updateUser(any())).thenReturn(result);

    webMockMvc.perform(put("/user")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(new ObjectMapper().writeValueAsString(result)))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.userName").value(result.getUserName()))
        .andExpect(jsonPath("$.email").value(result.getEmail()))
        .andExpect(jsonPath("$.firstName").value(result.getFirstName()))
        .andExpect(jsonPath("$.lastName").value(result.getLastName()))
        .andExpect(jsonPath("$.telephone").value(result.getTelephone()))
        .andExpect(jsonPath("$.address").value(result.getAddress()))
        .andExpect(jsonPath("$.country").value(result.getCountry()))
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  public void test_updateUser_NotFound() throws Exception {
    User result = new User("userName");
    result.setEmail("email@email.com");
    result.setFirstName("firstName");
    result.setLastName("lastName");
    result.setTelephone("123456789");
    result.setAddress("address");
    result.setCountry("country");

    UserNotFoundException exception = new UserNotFoundException("User not found.");
    when(defaultUserService.updateUser(any())).thenThrow(exception);

    webMockMvc.perform(put("/user")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(new ObjectMapper().writeValueAsString(result)))
        .andExpect(status().isNotFound())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.error").value(exception.getError().getValue()))
        .andExpect(jsonPath("$.error_description").value(exception.getMessage()))
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  public void test_updateUser_InvalidInput() throws Exception {
    User result = new User("userName");
    result.setEmail("email");
    result.setFirstName("firstName");
    result.setLastName("lastName");
    result.setTelephone("telephone");
    result.setAddress("address");
    result.setCountry("country");

    webMockMvc.perform(put("/user")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(new ObjectMapper().writeValueAsString(result)))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.error").value(UserErrorCode.INVALID_INPUT.getValue()))
        .andExpect(jsonPath("$.error_description").exists())
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  public void test_deleteUser_OK() throws Exception {
    User result = new User("userName");
    result.setEmail("email");
    result.setFirstName("firstName");
    result.setLastName("lastName");
    result.setTelephone("telephone");
    result.setAddress("address");
    result.setCountry("country");

    when(defaultUserService.deleteUser(any())).thenReturn(result);

    webMockMvc.perform(delete("/user/userName")
        .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.userName").value(result.getUserName()))
        .andExpect(jsonPath("$.email").value(result.getEmail()))
        .andExpect(jsonPath("$.firstName").value(result.getFirstName()))
        .andExpect(jsonPath("$.lastName").value(result.getLastName()))
        .andExpect(jsonPath("$.telephone").value(result.getTelephone()))
        .andExpect(jsonPath("$.address").value(result.getAddress()))
        .andExpect(jsonPath("$.country").value(result.getCountry()))
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  public void test_deleteUser_NotFound() throws Exception {
    UserNotFoundException exception = new UserNotFoundException("User not found.");
    when(defaultUserService.deleteUser(any())).thenThrow(exception);

    webMockMvc.perform(delete("/user/userName")
        .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isNotFound())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.error").value(exception.getError().getValue()))
        .andExpect(jsonPath("$.error_description").value(exception.getMessage()))
        .andDo(MockMvcResultHandlers.print());
  }

}
