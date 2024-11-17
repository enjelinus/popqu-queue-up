package com.pqu.user.repository.entity;

import com.google.i18n.phonenumbers.NumberParseException;
import com.pqu.common.domain.PhoneNumber;
import com.pqu.common.repository.entity.TimeBaseEntity;
import com.pqu.user.domain.User;
import com.pqu.user.domain.UserAuth;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "popqu_user")
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends TimeBaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long uid;
  private String name;
  private String phoneNumber;

  @Convert(converter = UserAuthConverter.class)
  private UserAuth auth;

  public UserEntity(User user) {
    this.uid = user.getId();
    this.name = user.getName();
    this.phoneNumber = user.getPhoneNumber();
    this.auth = user.getAuth();
  }

  public User toUser() throws NumberParseException {
    return User.builder()
        .id(uid)
        .name(name)
        .phoneNumber(PhoneNumber.createPhoneNumber(phoneNumber))
        .auth(auth)
        .build();
  }
}
