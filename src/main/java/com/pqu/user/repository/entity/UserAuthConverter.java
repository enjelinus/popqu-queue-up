package com.pqu.user.repository.entity;

import com.pqu.user.domain.UserAuth;
import jakarta.persistence.AttributeConverter;

public class UserAuthConverter implements AttributeConverter<UserAuth, String> {

  @Override
  public String convertToDatabaseColumn(UserAuth userAuth) {
    return userAuth.name();
  }

  @Override
  public UserAuth convertToEntityAttribute(String dbData) {
    return UserAuth.valueOf(dbData);
  }
}
