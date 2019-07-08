package pt.jmnpedrosa.samples.springboot.restful.error;

public class UserAlreadyExistsException extends UserException {

  public UserAlreadyExistsException() {
    super(UserErrorCode.USER_ALREADY_EXISTS);
  }

  public UserAlreadyExistsException(String message) {
    super(message, UserErrorCode.USER_ALREADY_EXISTS);
  }

}
