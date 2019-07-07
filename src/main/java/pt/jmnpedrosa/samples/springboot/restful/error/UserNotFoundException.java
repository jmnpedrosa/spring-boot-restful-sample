package pt.jmnpedrosa.samples.springboot.restful.error;

public class UserNotFoundException extends UserException {

  public UserNotFoundException() {
    super(UserErrorCode.USER_NOT_FOUND);
  }

  public UserNotFoundException(String message) {
    super(message, UserErrorCode.USER_NOT_FOUND);
  }

}
