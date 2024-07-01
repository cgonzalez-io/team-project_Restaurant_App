package entity.restaurant;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

/**
 * The StaffAssignment class represents a staff assignment in a restaurant.
 * It contains information such as assignment ID, staff ID, table ID, date, and time.
 */
public class StaffAssignment {

    private int assignmentID;
    private int staffID;
    private int tableID;
    private Date date;
    private Time time;

    // Constructors
    public StaffAssignment(int assignmentID, int staffID, int tableID, Date date, Time time) {
        this.assignmentID = assignmentID;
        this.staffID = staffID;
        this.tableID = tableID;
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        return (
            "StaffAssignment{" +
            "assignmentID=" +
            assignmentID +
            ", staffID=" +
            staffID +
            ", tableID=" +
            tableID +
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
            getAssignmentID() == that.getAssignmentID() &&
            getStaffID() == that.getStaffID() &&
            getTableID() == that.getTableID() &&
            Objects.equals(getDate(), that.getDate()) &&
            Objects.equals(getTime(), that.getTime())
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAssignmentID(), getStaffID(), getTableID(), getDate(), getTime());
    }

    // Getters and Setters
    public int getAssignmentID() {
        return assignmentID;
    }

    public void setAssignmentID(int assignmentID) {
        this.assignmentID = assignmentID;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
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
}
