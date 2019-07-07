package pt.jmnpedrosa.samples.springboot.restful.error;

/**
 * This enum holds all the error codes that can be returned in error responses
 */
public enum UserErrorCode {

  GENERIC_ERROR("generic_error"),
  USER_ERROR("user_error"),
  USER_NOT_FOUND("user_not_found");

  private final String value;

  UserErrorCode(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
