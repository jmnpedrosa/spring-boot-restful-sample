package pt.jmnpedrosa.samples.springboot.restful.model;

public class User {

  private String userName;
  private String email;
  private String firstName;
  private String lastName;
  private String telephone;
  private String address;
  private String country;

  public User(String userName) {
    this.userName = userName;
  }

  public User(String userName, String email, String firstName, String lastname) {
    this(userName);
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastname;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }
}
