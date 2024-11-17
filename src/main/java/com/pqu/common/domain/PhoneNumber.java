package com.pqu.common.domain;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.Phonenumber;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PhoneNumber {
  private String regionCode;
  private Phonenumber.PhoneNumber phoneNumber;

  public static PhoneNumber createPhoneNumber(String phoneNumber, String regionCode) throws NumberParseException {
    PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
    Phonenumber.PhoneNumber parsedNumber = phoneNumberUtil.parse(phoneNumber, regionCode);

    if (!phoneNumberUtil.isValidNumber(parsedNumber)) {
      throw new IllegalArgumentException("유효하지 않은 전화번호입니다.");
    }

    return new PhoneNumber(regionCode, parsedNumber);
  }

  public static PhoneNumber createPhoneNumber(String phoneNumber) throws NumberParseException {
    PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
    Phonenumber.PhoneNumber parsedNumber = phoneNumberUtil.parse(phoneNumber, null);

    if (!phoneNumberUtil.isValidNumber(parsedNumber)) {
      throw new IllegalArgumentException("유효하지 않은 전화번호입니다.");
    }
    String regionCode = phoneNumberUtil.getRegionCodeForNumber(parsedNumber);

    return new PhoneNumber(regionCode, parsedNumber);
  }

  public String getFormattedPhoneNumber() {
    PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
    return phoneNumberUtil.format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL);
  }

  public String getRegionCode() {
    return "+" + phoneNumber.getCountryCode();
  }

  public Phonenumber.PhoneNumber getPhoneNumber() {
    return phoneNumber;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PhoneNumber that = (PhoneNumber) o;
    return Objects.equals(regionCode, that.regionCode) && Objects.equals(
        phoneNumber, that.phoneNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(regionCode, phoneNumber);
  }
}
