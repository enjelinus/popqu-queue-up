package com.pqu.reservation.domain.common;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DatetimeInfo {
  private boolean isEdited;
  private LocalDate date;
  private LocalTime time;

  public DatetimeInfo() {
    this.isEdited = false;
    LocalDateTime now = LocalDateTime.now();
    this.date = now.toLocalDate();
    this.time = now.toLocalTime();
  }

  public DatetimeInfo(String reservationDtTm) {
    this.isEdited = false;
    if (reservationDtTm.contains("-")) {
      this.date = LocalDate.parse(reservationDtTm, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    if (reservationDtTm.contains(":")) {
      this.time = LocalTime.parse(reservationDtTm, DateTimeFormatter.ofPattern("HH:mm"));
    }
  }

  public DatetimeInfo(LocalDate reservationDt) {
    this.isEdited = false;
    this.date = reservationDt;
  }

  public DatetimeInfo(LocalTime reservationTm) {
    this.isEdited = false;
    this.time = reservationTm;
  }

  public void updateEditDateTime(LocalDateTime updatedDt) {
    this.isEdited = true;
    this.date = updatedDt.toLocalDate();
    this.time = updatedDt.toLocalTime();
  }

  public void updateDate(LocalDate date) {
    this.isEdited = true;
    this.date = date;
  }

  public void updateTime(LocalTime time) {
    this.isEdited = true;
    this.time = time;
  }

  public boolean isEdited() {
    return isEdited;
  }

  public LocalDate getDate() {
    return date;
  }

  public LocalTime getTime() {
    return time;
  }

  public LocalDateTime getDateTime() {
    return LocalDateTime.of(date, time);
  }
}
