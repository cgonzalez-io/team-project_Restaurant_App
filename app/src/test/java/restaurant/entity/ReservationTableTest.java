package restaurant.entity;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReservationTableTest {

    @Test
    public void test_initialization_ReservationTable(){
        ReservationTable rt = new ReservationTable();
        assertNotNull(rt);
    }

    @Test
    public void test_initializes_ReservationID_to_1(){
        ReservationTable rt = new ReservationTable(1, 0);
        assertEquals(1,rt.getReservationId());

    }

    @Test
    public void test_initializes_TableID_to_1(){
        ReservationTable rt = new ReservationTable(0, 1);
        assertEquals(1,rt.getTableId());
    }

    @Test
    public void test_Negative_ReservationId() {
        ReservationTable reservationTable = new ReservationTable(-1, 5);
        assertEquals(-1, reservationTable.getReservationId());
    }

    @Test
    public void test_Negative_TableId() {
        ReservationTable reservationTable = new ReservationTable(1, -5);
        assertEquals(-5, reservationTable.getTableId());
    }
    @Test
    public void test_ToString_Method() {
        ReservationTable reservationTable = new ReservationTable(3, 8);
        String expected = "ReservationTable{reservationId=3, tableId=8}";
        assertEquals(expected, reservationTable.toString());
    }

    @Test
    public void test_setting_and_getting_TableID_and_ReservationID() {
        ReservationTable reservationTable = new ReservationTable();
        reservationTable.setReservationId(2);
        reservationTable.setTableId(10);

        assertEquals(2, reservationTable.getReservationId());
        assertEquals(10, reservationTable.getTableId());
    }

    @Test
    public void test_Equality_SameValues() {
        ReservationTable rt1 = new ReservationTable(1, 2);
        ReservationTable rt2 = new ReservationTable(1, 2);
        assertEquals(rt1, rt2);
    }


    @Test
    public void test_Inequality_Different_ReservationId() {
        ReservationTable rt1 = new ReservationTable(1, 2);
        ReservationTable rt2 = new ReservationTable(2, 2);
        assertNotEquals(rt1, rt2);
    }

    @Test
    public void test_Inequality_Different_TableId() {
        ReservationTable rt1 = new ReservationTable(1, 2);
        ReservationTable rt2 = new ReservationTable(1, 3);
        assertNotEquals(rt1, rt2);
    }
    @Test
    public void test_ZeroValue() {
        ReservationTable rt = new ReservationTable(0, 0);
        assertEquals(0, rt.getReservationId());
        assertEquals(0, rt.getTableId());
    }
    @Test
    public void test_MaxValue() {
        ReservationTable rt = new ReservationTable(Integer.MAX_VALUE, Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, rt.getReservationId());
        assertEquals(Integer.MAX_VALUE, rt.getTableId());
    }

    @Test
    public void test_MinValue() {
        ReservationTable rt = new ReservationTable(Integer.MIN_VALUE, Integer.MIN_VALUE);
        assertEquals(Integer.MIN_VALUE, rt.getReservationId());
        assertEquals(Integer.MIN_VALUE, rt.getTableId());
    }



}
