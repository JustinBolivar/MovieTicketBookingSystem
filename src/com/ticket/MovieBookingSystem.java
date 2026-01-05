package com.ticket;

import java.util.ArrayList;

public class MovieBookingSystem extends BookingSystem {
    private ArrayList<Movie> showTimes;
    // private int availableTickets;

    public MovieBookingSystem() {
        showTimes = new ArrayList<>();
        showTimes.add(new Movie("12:00 PM", 40));
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

    }

    public void cancelReservation(String showTime, int tickets) {

    }

    public static void main(String[] args) {
        MovieBookingSystem movie = new MovieBookingSystem();
        String time = "12:00 PM";
        movie.checkAvailability(time);
    }
}
