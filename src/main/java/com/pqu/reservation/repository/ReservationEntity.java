package com.pqu.reservation.repository;

import com.google.i18n.phonenumbers.NumberParseException;
import com.pqu.common.domain.PositiveIntegerCounter;
import com.pqu.common.repository.entity.TimeBaseEntity;
import com.pqu.reservation.domain.Reservation;
import com.pqu.reservation.domain.common.DatetimeInfo;
import com.pqu.reservation.domain.content.MemoContent;
import com.pqu.reservation.domain.content.ReservationState;
import com.pqu.user.domain.User;
import com.pqu.user.repository.entity.UserEntity;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "popqu_reservation")
@NoArgsConstructor
@AllArgsConstructor
public class ReservationEntity extends TimeBaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long rid;

  @ManyToOne
  @JoinColumn(name = "holder_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
  private UserEntity holder;

  private Long rsid;
  private Integer peopleCount;
  private String memo;
  private String reservationDt;
  private String reservationTm;

  @Convert(converter = ReservationStateConverter.class)
  private ReservationState state;

  public ReservationEntity(Reservation reservation) {
    this.rid = reservation.getId();
    this.holder = new UserEntity(reservation.getHolder());
    this.peopleCount = reservation.getPeopleCounter();
    this.memo = reservation.getMemo();
    this.reservationDt = reservation.getReservationDt();
    this.reservationTm = reservation.getReservationTm();
  }

  public Reservation toReservation() throws NumberParseException {
    return Reservation.builder()
        .id(rid)
        .holder(holder.toUser())
        .peopleCount(new PositiveIntegerCounter(peopleCount))
        .memo(new MemoContent(memo))
        .reservationDt(new DatetimeInfo(reservationDt))
        .reservationTm(new DatetimeInfo(reservationTm))
        .build();
  }
}
