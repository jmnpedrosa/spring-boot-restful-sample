package pt.jmnpedrosa.samples.springboot.restful.error;

/**
 * A custom exception class that contains a String error code to be shown in error responses.
 */
public class UserException extends Exception {

  private static final long serialVersionUID = 1L;

  private final UserErrorCode error;

  public UserException() {
    this.error = UserErrorCode.USER_ERROR;
  }

  public UserException(String message) {
    super(message);
    this.error = UserErrorCode.USER_ERROR;
  }

  public UserException(Throwable cause) {
    super(cause);
    this.error = UserErrorCode.USER_ERROR;
  }

  public UserException(String message, Throwable cause) {
    super(message, cause);
    this.error = UserErrorCode.USER_ERROR;
  }

  public UserException(final UserErrorCode error) {
    this.error = error;
  }

  public UserException(String message, final UserErrorCode error) {
    super(message);
    this.error = error;
  }

  public UserException(Throwable cause, final UserErrorCode error) {
    super(cause);
    this.error = error;
  }

  public UserException(String message, Throwable cause, final UserErrorCode error) {
    super(message, cause);
    this.error = error;
  }

  public UserErrorCode getError() {
    return this.error;
  }

}
