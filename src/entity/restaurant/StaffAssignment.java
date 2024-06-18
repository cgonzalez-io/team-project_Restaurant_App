package entity.restaurant;

import java.sql.Date;
import java.sql.Time;

/**
 * The StaffAssignment class represents a staff assignment object with their unique ID, staff ID, table ID, date, and time.
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
