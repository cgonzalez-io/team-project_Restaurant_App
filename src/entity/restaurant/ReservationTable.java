package entity.restaurant;

import java.util.Objects;

/**
 * The ReservationTable class represents a reservation-table assignment in a restaurant.
 * It contains information such as reservation ID and table ID.
 */
public class ReservationTable {

    private int reservationId;
    private int tableId;

    public ReservationTable() {}

    public ReservationTable(int reservationId, int tableId) {
        this.reservationId = reservationId;
        this.tableId = tableId;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    @Override
    public String toString() {
        return "ReservationTable{" + "reservationId=" + reservationId + ", tableId=" + tableId + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReservationTable that)) return false;
        return reservationId == that.reservationId && tableId == that.tableId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservationId, tableId);
    }
}