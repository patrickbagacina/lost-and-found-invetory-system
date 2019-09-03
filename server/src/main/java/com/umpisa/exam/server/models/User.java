package com.umpisa.exam.server.models;

import com.umpisa.exam.server.InvalidCredentialsException;
import com.umpisa.exam.server.jpa.Repository;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "[user]")
public class User extends Model {
  private static final String USERNAME = "root";
  private static final String PASSWORD = "root";

  private static final Repository<User> repo = new Repository<>(User.class);

  @Transient
  private String username;

  @Column(nullable = false, columnDefinition = "varchar(255)")
  @NotNull
  @Size(min = 1, max = 255)
  @NotBlank
  private String firstName;

  @Column(nullable = false, columnDefinition = "varchar(255)")
  @NotNull
  @Size(min = 1, max = 255)
  @NotBlank
  private String lastName;

  @Column(nullable = false, columnDefinition = "varchar(255)")
  @NotNull
  @Size(min = 1, max = 74)
  @NotBlank
  @Pattern(
    regexp = "^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$",
    message = "invalid format"
  )
  private String email;

  @Transient
  private String password;

  @Column(nullable = false, columnDefinition = "varchar(255)")
  @NotNull
  @NotBlank
  private String mobileNumber;

  @Column(nullable = false, columnDefinition = "varchar(255)")
  @NotNull
  @Size(min = 1, max = 255)
  @NotBlank
  private String typeOfId;

  @Column(nullable = false, columnDefinition = "varchar(255)")
  @NotNull
  @Size(min = 1, max = 255)
  @NotBlank
  private String idNumber;

  public String getTypeOfId() {
    return typeOfId;
  }

  public void setTypeOfId(String typeOfId) {
    this.typeOfId = typeOfId;
  }

  public User withTypeOfId(String arg) {
    setTypeOfId(arg);
    return this;
  }

  public String getIdNumber() {
    return idNumber;
  }

  public void setIdNumber(String arg) {
    this.idNumber = arg;
  }

  public User withIdNumber(String arg) {
    setIdNumber(arg);
    return this;
  }

  public String getMobileNumber() {
    return mobileNumber;
  }

  public void setMobileNumber(String arg) {
    this.mobileNumber = arg;
  }

  public User withMobileNumber(String arg) {
    setMobileNumber(arg);
    return this;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String arg) {
    this.username = arg;
  }

  public User withUsername(String arg) {
    setUsername(arg);
    return this;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String arg) {
    this.firstName = arg;
  }

  public User withFirstName(String arg) {
    setFirstName(arg);
    return this;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String arg) {
    this.lastName = arg;
  }

  public User withLastName(String arg) {
    setLastName(arg);
    return this;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String arg) {
    this.email = arg;
  }

  public User withEmail(String arg) {
    setEmail(arg);
    return this;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String arg) {
    this.password = arg;
  }

  public User withPassword(String arg) {
    setPassword(arg);
    return this;
  }

  // TODO: Improve authentication
  public static User authenticate(String username, String password) {
    if(username.equals(USERNAME) && password.equals(PASSWORD)) {
      return new User()
        .withUsername(username);
    }

    throw new InvalidCredentialsException();
  }
}
