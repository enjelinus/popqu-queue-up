package com.pqu.reservation.domain;

import com.pqu.common.domain.PositiveIntegerCounter;
import com.pqu.reservation.domain.common.DatetimeInfo;
import com.pqu.reservation.domain.content.Content;
import com.pqu.reservation.domain.content.ReservationState;
import com.pqu.user.domain.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Reservation {
  private final Long id;
  private final User holder;
  public final DatetimeInfo reservationDt;
  public final DatetimeInfo reservationTm;
  private final PositiveIntegerCounter peopleCount;
  private final Content memo;
  private final ReservationState status;

  public Reservation(Long id, User holder, LocalDate reservationDt, LocalTime reservationTm,
      PositiveIntegerCounter peopleCount, Content memo,
      ReservationState status) {
    this.id = id;
    this.holder = holder;
    this.reservationDt = new DatetimeInfo(reservationDt);
    this.reservationTm = new DatetimeInfo(reservationTm);
    this.peopleCount = peopleCount;
    this.memo = memo;
    this.status = status;
  }

  public int getPeopleCounter() {
    return peopleCount.getCount();
  }

  public String getMemo() {
    return memo.getContentText();
  }

  public String getReservationDt() {
    return reservationDt.getDate().toString();
  }

  public String getReservationTm() {
    return reservationDt.getTime().toString();
  }

  public void updateReservation(LocalDateTime updatedDt, User editor, String updateContent) {
    if (!this.holder.equals(editor)) {
      throw new IllegalArgumentException();
    }
    this.reservationDt.updateEditDateTime(updatedDt);
    this.memo.updateContent(updateContent);
  }
}
