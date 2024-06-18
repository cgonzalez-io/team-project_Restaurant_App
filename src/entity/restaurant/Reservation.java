package entity.restaurant;

import java.sql.Date;
import java.sql.Time;

/**
 * The Reservation class represents a reservation object with their unique ID, customer ID, table ID, date, time, and number of people.
 */
public class Reservation {

    private int reservationID;
    private int customerID;
    private int tableID;
    private Date date;
    private Time time;
    private int numberOfPeople;
    private Reservation next;

    // Constructors
    public Reservation(int reservationID, int customerID, int tableID, Date date, Time time, int numberOfPeople) {
        this.reservationID = reservationID;
        this.customerID = customerID;
        this.tableID = tableID;
        this.date = date;
        this.time = time;
        this.numberOfPeople = numberOfPeople;
    }

    // Getters and Setters
    public int getReservationID() {
        return reservationID;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getTableID() {
        return tableID;
    }

    public void setTableID(int tableID) {
        this.tableID = tableID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public Reservation getNext() {
        return next;
    }

    public void setNext(Reservation next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return (
            "Reservation{" +
            "reservationID=" +
            reservationID +
            ", customerID=" +
            customerID +
            ", tableID=" +
            tableID +
            ", date=" +
            date +
            ", time=" +
            time +
            ", numberOfPeople=" +
            numberOfPeople +
            '}'
        );
    }
}
