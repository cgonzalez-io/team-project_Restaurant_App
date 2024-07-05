package restaurant.entity;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

public class StaffAssignment {

    private int tableID;
    private int staffID;
    private Date date;
    private Time time;
    public StaffAssignment(int tableID, int staffID, Date date, Time time) {
        this.tableID = tableID;
        this.staffID = staffID;
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        return (
            "StaffAssignment{" +
                "tableID=" +
                tableID +
                ", staffID=" +
                staffID +
                ", date=" +
                date +
                ", time=" +
                time +
                '}'
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StaffAssignment that)) return false;
        return (
            getTableID() == that.getTableID() &&
                getStaffID() == that.getStaffID() &&
                Objects.equals(getDate(), that.getDate()) &&
                Objects.equals(getTime(), that.getTime())
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTableID(), getStaffID(), getDate(), getTime());
    }

    // Getters and Setters

    public int getTableID() {
        return tableID;
    }

    public void setTableID(int tableID) {
        this.tableID = tableID;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
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
}
