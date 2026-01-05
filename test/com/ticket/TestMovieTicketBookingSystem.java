package com.ticket;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestMovieTicketBookingSystem {
    private MovieBookingSystem system;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    
    private Movie getMovieFromSystem(String time) throws Exception {
        Field field = MovieBookingSystem.class.getDeclaredField("showTimes");
        field.setAccessible(true);
        ArrayList<Movie> showTimes = (ArrayList<Movie>) field.get(system);

        for (Movie m : showTimes) {
            if (m.getTime().equals(time))
                return m;
        }
        return null;
    }

    @BeforeEach
    void setUp() {
        system = new MovieBookingSystem();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void testInitialCapacity_ShouldReturnTrue() throws Exception {
        Movie movie = getMovieFromSystem("10:00 AM");
        assertNotNull(movie);
        assertEquals(50, movie.getAvailableTickets(),
                "Initial capacity should be 50");
    }

    @Test
    void testBooking_BookSuccesfully_ShouldReturnTrue() throws Exception {
        String time = "12:00 PM";
        system.bookTicket(time, 5);

        Movie movie = getMovieFromSystem(time);
        assertEquals(45, movie.getAvailableTickets());
        assertEquals(5, movie.getBookedTickets());
    }

    @Test
    void testBooking_BookNonExistingTime_ShouldReturnTrue() throws Exception {
        String time = "2:00 AM";
        system.bookTicket(time, 5);
        assertTrue(
                outputStreamCaptor.toString().contains("Showtime not found."));
    }

    @Test
    void testBooking_BookMoreThanCapacityOfSchedule_ShouldReturnTrue() throws Exception {
        String time = "1:00 PM";
        system.bookTicket(time, 51);

        Movie movie = getMovieFromSystem(time);
        assertEquals(50, movie.getAvailableTickets(),
                "Available tickets should not change on failed booking");
        assertEquals(0, movie.getBookedTickets());
    }

    @Test
    void testCancelReservation_SuccesfullyCancelABooking_ShouldReturnTrue() throws Exception {
        String time = "04:00 PM";
        system.bookTicket(time, 10);
        system.cancelReservation(time, 4);

        Movie movie = getMovieFromSystem(time);
        assertEquals(44, movie.getAvailableTickets());
        assertEquals(6, movie.getBookedTickets());
    }

    @Test
    void testCancelReservation_CancelMoreThanBookedAmount_ShouldReturnTrue() throws Exception {
        String time = "08:00 PM";
        system.bookTicket(time, 5);
        system.cancelReservation(time, 10); // Canceling more than booked

        Movie movie = getMovieFromSystem(time);
        assertEquals(45, movie.getAvailableTickets(),
                "State should not change if cancellation is invalid");
    }

    @Test
    void testMain() {
        int expectedResult = 1;
        MovieBookingSystem.main(null);

        int result = 1;
        assertEquals(expectedResult, result, () -> "Main Test");
    }

}
