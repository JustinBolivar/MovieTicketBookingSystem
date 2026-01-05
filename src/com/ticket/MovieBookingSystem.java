package com.ticket;

import java.util.ArrayList;

public class MovieBookingSystem extends BookingSystem {
    private ArrayList<Movie> showTimes;

    public MovieBookingSystem() {
        showTimes = new ArrayList<>();
        showTimes.add(new Movie("10:00 AM", 20));
        showTimes.add(new Movie("12:00 PM", 40));
        showTimes.add(new Movie("1:00 PM", 30));
        showTimes.add(new Movie("04:00 PM", 25));
        showTimes.add(new Movie("08:00 PM", 10));
    }

    public void checkAvailability(String showTime) {
        for (Movie show : showTimes) {
            if (show.time.equals(showTime)) {
                System.out.println("Available Tickets for " + show.time
                        + "  is : " + show.availableTickets);
            }
        }
    }

    public void bookTicket(String showTime, int tickets) {
        for (Movie show : showTimes) {
            if (show.time.equals(showTime)) {
                if (show.availableTickets >= tickets) {
                    show.availableTickets -= tickets;
                    show.bookedTickets += tickets;
                    System.out.println(tickets
                            + " tickets succesfully booked for " + showTime);
                } else {
                    System.out.println(
                            "Not enough tickets available for this showtime!");
                }
                return;
            }
        }
        System.out.println("Showtime not found.");
    }

    public void cancelReservation(String showTime, int tickets) {
        for (Movie show : showTimes) {
            if (show.time.equals(showTime)) {
                if (tickets <= show.bookedTickets) {
                    show.availableTickets += tickets;
                    show.bookedTickets -= tickets;
                    System.out.println(tickets
                            + " tickets succesfully cancelled for " + showTime);
                } else {
                    System.out.println(
                            "Invalid operation (Attempt to cancel more tickets than booked)");
                }
                return;
            }
        }
    }

    public void displaySchedule() {
        System.out.println("\n--- Movie Schedule ---");
        for (Movie show : showTimes) {
            System.out.println("Time: " + show.time + " | Seats Available: "
                    + show.availableTickets);
        }
        System.out.println("\n----------------------");
    }

    public static void main(String[] args) {
        MovieBookingSystem movie = new MovieBookingSystem();
        movie.displaySchedule();
        String time = "10:00 AM";
        String time2 = "1:00 PM";
        movie.bookTicket(time, 5);
        movie.bookTicket(time, 100);
        movie.cancelReservation(time, 3);
        movie.bookTicket(time2, 2);
        movie.cancelReservation(time, 5);

    }
}
