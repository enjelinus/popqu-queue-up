package com.pqu.user.domain;

import com.pqu.common.domain.PhoneNumber;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

  private Long id;
  private String name;
  private PhoneNumber phoneNumber;
  private UserAuth auth;

  public User(Long id, String name, PhoneNumber phoneNumber) {
    if (name == null || phoneNumber.getRegionCode() == null
        || phoneNumber.getPhoneNumber() == null) {
      throw new IllegalArgumentException("Name, phone number, and region code must not be null.");
    }
    this.id = id;
    this.name = name;
    this.phoneNumber = new PhoneNumber(phoneNumber.getRegionCode(), phoneNumber.getPhoneNumber());
  }

  public String getRegionCode() {
    return phoneNumber.getRegionCode();
  }

  public String getPhoneNumber() {
    return phoneNumber.getFormattedPhoneNumber();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(id, user.id) && Objects.equals(name, user.name)
        && Objects.equals(phoneNumber, user.phoneNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, phoneNumber);
  }
}
