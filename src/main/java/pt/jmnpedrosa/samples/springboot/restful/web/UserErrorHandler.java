package pt.jmnpedrosa.samples.springboot.restful.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pt.jmnpedrosa.samples.springboot.restful.error.UserErrorCode;
import pt.jmnpedrosa.samples.springboot.restful.error.UserErrorResponse;
import pt.jmnpedrosa.samples.springboot.restful.error.UserException;
import pt.jmnpedrosa.samples.springboot.restful.error.UserNotFoundException;

/**
 * Logic for returning custom HTTP status and messages for each exception.
 */
@ControllerAdvice(assignableTypes = {UserRestController.class})
public class UserErrorHandler {

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<UserErrorResponse> handleError(UserNotFoundException e) {
    UserErrorResponse response = new UserErrorResponse();
    response.setError(e.getError().getValue());
    response.setDescription(e.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
  }

  @ExceptionHandler(UserException.class)
  public ResponseEntity<UserErrorResponse> handleError(UserException e) {
    UserErrorResponse response = new UserErrorResponse();
    response.setError(e.getError().getValue());
    response.setDescription(e.getMessage());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<UserErrorResponse> handleError(Exception e) {
    UserErrorResponse response = new UserErrorResponse();
    response.setError(UserErrorCode.GENERIC_ERROR.getValue());
    response.setDescription(e.getMessage());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
  }
}
