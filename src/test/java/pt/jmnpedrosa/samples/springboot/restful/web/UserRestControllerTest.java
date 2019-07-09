package pt.jmnpedrosa.samples.springboot.restful.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
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
        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
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
        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(status().isNotFound())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.error").value(exception.getError().getValue()))
        .andExpect(jsonPath("$.error_description").value(exception.getMessage()))
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  public void test_getUser_UserException() throws Exception {
    UserException exception = new UserException("Unspecified error.");
    when(defaultUserService.getUser(any())).thenThrow(exception);

    webMockMvc.perform(get("/user/userName")
        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(status().isInternalServerError())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
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
        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
        .content(new ObjectMapper().writeValueAsString(result)))
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
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
        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
        .content(new ObjectMapper().writeValueAsString(result)))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
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
        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
        .content(new ObjectMapper().writeValueAsString(result)))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
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
        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
        .content(new ObjectMapper().writeValueAsString(result)))
        .andExpect(status().isNotFound())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
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
        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
        .content(new ObjectMapper().writeValueAsString(result)))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.error").value(UserErrorCode.INVALID_INPUT.getValue()))
        .andExpect(jsonPath("$.error_description").exists())
        .andDo(MockMvcResultHandlers.print());
  }

}
