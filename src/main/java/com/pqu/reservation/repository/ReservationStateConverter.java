package com.pqu.reservation.repository;

import com.pqu.reservation.domain.content.ReservationState;
import jakarta.persistence.AttributeConverter;

public class ReservationStateConverter implements AttributeConverter<ReservationState, String> {

  @Override
  public String convertToDatabaseColumn(ReservationState attribute) {
    return attribute.name();
  }

  @Override
  public ReservationState convertToEntityAttribute(String dbData) {
    return ReservationState.valueOf(dbData);
  }
}
