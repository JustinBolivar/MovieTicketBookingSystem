package com.ticket;

import java.util.ArrayList;

public class MovieBookingSystem extends BookingSystem {
    /**
     * Array list of movie showTimes.
     */
    private ArrayList<Movie> showTimes;

    /**
     * Create default set of movie schedule.
     */
    public MovieBookingSystem() {
        final int capacity = 50;
        showTimes = new ArrayList<>();
        showTimes.add(new Movie("10:00 AM", capacity));
        showTimes.add(new Movie("12:00 PM", capacity));
        showTimes.add(new Movie("1:00 PM", capacity));
        showTimes.add(new Movie("04:00 PM", capacity));
        showTimes.add(new Movie("08:00 PM", capacity));
    }

    /**
     * getter for the movies to be accessible in the unit test.
     * 
     * @param time
     * @return null
     */
    public Movie getMovie(final String time) {
        for (Movie m : showTimes) {
            if (m.getTime().equals(time)) {
                return m;
            }
        }
        return null;
    }

    /**
     * Function to check the availability of a specific movie schedule.
     * 
     * @param showTime
     */
    public void checkAvailability(final String showTime) {
        for (Movie show : showTimes) {
            if (show.getTime().equals(showTime)) {
                System.out.println("Available Tickets for " + show.getTime()
                        + "  is : " + show.getAvailableTickets());
            }
        }
    }

    /**
     * Function to book specific number of tickets for a specific movie
     * schedule.
     * 
     * @param showTime
     * @param tickets
     */
    public void bookTicket(final String showTime, final int tickets) {
        String timeRegex = "^(0?[1-9]|1[0-2]):[0-5][0-9] (AM|PM)$";

        if (!showTime.matches(timeRegex)) {
            System.out.println(
                    "Invalid time format. Please use 'HH:MM AM/PM' (e.g., 05:30 PM).");
            return;
        }

        for (Movie show : showTimes) {
            if (show.getTime().equals(showTime)) {
                if (show.getAvailableTickets() >= tickets) {
                    int newAvailable = show.getAvailableTickets() - tickets;
                    int newBooked = show.getBookedTickets() + tickets;

                    show.setAvailableTickets(newAvailable);
                    show.setBookedTickets(newBooked);

                    System.out.println(tickets
                            + " tickets successfully booked for " + showTime);
                } else if (show.getAvailableTickets() == 0) {
                    System.out.println("Tickets are sold out!");
                } else {
                    System.out.println(
                            "Not enough tickets available for this showtime!");
                }
                return;
            }
        }
        System.out.println("Showtime not found.");
    }

    /**
     * Function to cancel the previously booked tickets for a certain movie
     * schedule.
     * 
     * @param showTime
     * @param tickets
     */
    public void cancelReservation(final String showTime, final int tickets) {
        String timeRegex = "^(0?[1-9]|1[0-2]):[0-5][0-9] (AM|PM)$";

        if (!showTime.matches(timeRegex)) {
            System.out.println(
                    "Invalid time format. Please use 'HH:MM AM/PM' (e.g., 05:30 PM).");
            return;
        }

        for (Movie show : showTimes) {
            if (show.getTime().equals(showTime)) {
                if (tickets <= show.getBookedTickets()) {
                    int newAvailable = show.getAvailableTickets() + tickets;
                    int newBooked = show.getBookedTickets() - tickets;

                    show.setAvailableTickets(newAvailable);
                    show.setBookedTickets(newBooked);
                    System.out.println(
                            tickets + " tickets successfully cancelled for "
                                    + showTime);
                    return;
                } else {
                    System.out.println(
                            "Invalid operation (Attempt to cancel more tickets than booked)");
                    return;
                }
            }
        }
        System.out.println("Show time " + showTime + " not found.");
    }

    /**
     * Main function to test and run program.
     * 
     * @param args
     */
    public static void main(final String[] args) {
        MovieBookingSystem movie = new MovieBookingSystem();
        String am = "10:00 AM";
        String pm = "1:00 PM";
        final int ticket = 5;
        final int ticket2 = 2;
        final int ticket3 = 3;
        final int over = 100;
        movie.checkAvailability(am);
        movie.bookTicket(am, ticket);
        movie.bookTicket(am, over);
        movie.cancelReservation(am, ticket3);
        movie.bookTicket(pm, ticket2);
        movie.cancelReservation(am, ticket);

    }
}
