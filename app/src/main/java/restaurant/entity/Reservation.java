package restaurant.entity;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

/**
 * The Reservation class represents a reservation in a restaurant.
 * It contains information such as reservation ID, customer ID, table ID,
 * date, time, and number of people.
 */
public class Reservation {

  private int reservationID;
  private int customerID;
  private Date date;
  private Time time;
  private int numberOfPeople;
  private Reservation next;

  // Constructors
  public Reservation(int reservationID, int customerID, Date date, Time time,
                     int numberOfPeople) {
    this.reservationID = reservationID;
    this.customerID = customerID;
    this.date = date;
    this.time = time;
    this.numberOfPeople = numberOfPeople;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof Reservation that))
      return false;
    return (getReservationID() == that.getReservationID() &&
            getCustomerID() == that.getCustomerID() &&
            getNumberOfPeople() == that.getNumberOfPeople() &&
            Objects.equals(getDate(), that.getDate()) &&
            Objects.equals(getTime(), that.getTime()) &&
            Objects.equals(getNext(), that.getNext()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(getReservationID(), getCustomerID(), getDate(),
                        getTime(), getNumberOfPeople(), getNext());
  }

  // Getters and Setters
  public int getReservationID() { return reservationID; }

  public void setReservationID(int reservationID) {
    this.reservationID = reservationID;
  }

  public int getCustomerID() { return customerID; }

  public void setCustomerID(int customerID) { this.customerID = customerID; }

  public Date getDate() { return date; }

  public void setDate(Date date) { this.date = date; }

  public Time getTime() { return time; }

  public void setTime(Time time) { this.time = time; }

  public int getNumberOfPeople() { return numberOfPeople; }

  public void setNumberOfPeople(int numberOfPeople) {
    this.numberOfPeople = numberOfPeople;
  }

  public Reservation getNext() { return next; }

  public void setNext(Reservation next) { this.next = next; }

  @Override
  public String toString() {
    return ("Reservation{"
            + "reservationID=" + reservationID + ", customerID=" + customerID +
            ", date=" + date + ", time=" + time +
            ", numberOfPeople=" + numberOfPeople + '}');
  }
}