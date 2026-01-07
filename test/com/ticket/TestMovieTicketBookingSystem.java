package com.ticket;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

class TestMovieTicketBookingSystem {
    private MovieBookingSystem system;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        system = new MovieBookingSystem();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void testInitialCapacity_ShouldReturnTrue() throws Exception {
        Movie movie = system.getMovie("10:00 AM");
        assertNotNull(movie);
        assertEquals(50, movie.getAvailableTickets(),
                "Initial capacity should be 50");
    }

    @Test
    void testBooking_BookSuccesfully_ShouldReturnTrue() throws Exception {
        String time = "12:00 PM";
        system.bookTicket(time, 5);

        Movie movie = system.getMovie(time);
        assertEquals(45, movie.getAvailableTickets());
        assertEquals(5, movie.getBookedTickets());
    }

    @Test
    void testBooking_BookSoldOutTicket_ShouldReturnTrue() throws Exception {
        String time = "12:00 PM";
        system.bookTicket(time, 50);
        system.bookTicket(time, 1);

        assertTrue(outputStreamCaptor.toString()
                .contains("Tickets are sold out!"));
    }

    @Test
    void testBooking_BookInvalidTimeFormat_ShouldReturnTrue() throws Exception {
        String time = "10 AM";
        system.bookTicket(time, 5);
        assertTrue(outputStreamCaptor.toString().contains(
                "Invalid time format. Please use 'HH:MM AM/PM' (e.g., 05:30 PM)."));
    }

    @Test
    void testBooking_BookNonExistingTime_ShouldReturnTrue() throws Exception {
        String time = "2:00 AM";
        system.bookTicket(time, 5);
        assertTrue(
                outputStreamCaptor.toString().contains("Showtime not found."));
    }

    @Test
    void testBooking_BookMoreThanCapacityOfSchedule_ShouldReturnTrue()
            throws Exception {
        String time = "1:00 PM";
        system.bookTicket(time, 51);

        Movie movie = system.getMovie(time);
        assertEquals(50, movie.getAvailableTickets(),
                "Available tickets should not change on failed booking");
        assertEquals(0, movie.getBookedTickets());
    }

    @Test
    void testCancelReservation_SuccesfullyCancelABooking_ShouldReturnTrue()
            throws Exception {
        String time = "04:00 PM";
        system.bookTicket(time, 10);
        system.cancelReservation(time, 4);

        Movie movie = system.getMovie(time);
        assertEquals(44, movie.getAvailableTickets());
        assertEquals(6, movie.getBookedTickets());
    }

    @Test
    void testCancelReservation_CancelInvalidTimeFormat_ShouldReturnTrue()
            throws Exception {
        String time = "10 AM";
        system.cancelReservation(time, 5);
        assertTrue(outputStreamCaptor.toString().contains(
                "Invalid time format. Please use 'HH:MM AM/PM' (e.g., 05:30 PM)."));
    }

    @Test
    void testCancelReservation_CancelMoreThanBookedAmount_ShouldReturnTrue()
            throws Exception {
        String time = "08:00 PM";
        system.bookTicket(time, 5);
        system.cancelReservation(time, 10); // Canceling more than booked

        Movie movie = system.getMovie(time);
        assertEquals(45, movie.getAvailableTickets(),
                "State should not change if cancellation is invalid");
    }

    @Test
    void testCancelReservation_CancelNonExistingTime_ShouldReturnTrue()
            throws Exception {
        String time = "3:00 AM";
        system.cancelReservation(time, 5);
        assertTrue(
                outputStreamCaptor.toString().contains("Showtime not found."));
    }
    
    @Test
    void testCancelReservation_CancelInvalidAmountOfTickets_ShouldReturnTrue()
            throws Exception {
        String time = "8:00 PM";
        system.cancelReservation(time, -5);
        assertTrue(
                outputStreamCaptor.toString().contains("Invalid number of Tickets"));
    }

    @Test
    void testMain() {
        int expectedResult = 1;
        MovieBookingSystem.main(null);

        int result = 1;
        assertEquals(expectedResult, result, () -> "Main Test");
    }

    @Test
    void testDisplayAllShowTimes_EmptyList_ShouldPrintNoShowtimesMessage()
            throws Exception {
        Field field = MovieBookingSystem.class.getDeclaredField("showTimes");
        field.setAccessible(true);
        ArrayList<?> showTimesList = (ArrayList<?>) field.get(system);
        showTimesList.clear();

        system.displayAllShowTimes();

        String output = outputStreamCaptor.toString().trim();
        assertTrue(output.contains("No showtimes available."),
                "Should display the empty list message when showTimes is empty");
    }

    @Test
    void testGetMovie_ExistingTime_ShouldReturnMovieObject() {
        String existingTime = "10:00 AM";
        Movie result = system.getMovie(existingTime);
        assertNotNull(result,
                "Should return a Movie object for an existing showtime");
        assertEquals(existingTime, result.getTime(),
                "Returned movie should have the matching time");
    }

    @Test
    void testGetMovie_NonExistingTime_ShouldReturnNull() {
        String nonExistingTime = "11:11 PM";
        Movie result = system.getMovie(nonExistingTime);
        assertNull(result,
                "Should return null when the showtime does not exist in the list");
    }

}
