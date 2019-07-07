package pt.jmnpedrosa.samples.springboot.restful.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Definition of the format of JSON responses in case of error
 */
@JsonInclude(Include.NON_NULL)
public class UserErrorResponse {

  private String error;
  private String description;

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  @JsonProperty("error_description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "UserErrorResponse{" +
        "error='" + error + '\'' +
        ", description='" + description + '\'' +
        '}';
  }
}
