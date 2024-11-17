package com.pqu.user.domain;

import com.pqu.common.domain.PhoneNumber;
import com.google.i18n.phonenumbers.NumberParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
  private PhoneNumber validPhoneNumber;

  @BeforeEach
  void setUp() throws NumberParseException {
    // 유효한 전화번호 생성
    validPhoneNumber = PhoneNumber.createPhoneNumber("+82 1012345678", "KR");
  }

  @Test
  void givenNewUser_whenCreate_thenReturnUSer() throws NumberParseException {
    // given
    String name = "test";

    // when
    User newUser = new User(1L, name, validPhoneNumber);

    // 필드 값이 정상적으로 설정되었는지 확인
    assertEquals(1L, newUser.getId());
    assertEquals("test", newUser.getName());
    assertEquals(validPhoneNumber.getFormattedPhoneNumber(), newUser.getPhoneNumber());
  }

}
