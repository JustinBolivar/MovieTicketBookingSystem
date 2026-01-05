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
     * Function to check the availability of a specific movie schedule.
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
     * @param showTime
     * @param tickets
     */
    public void bookTicket(final String showTime, final int tickets) {
        for (Movie show : showTimes) {
            if (show.getTime().equals(showTime)) {
                if (show.getAvailableTickets() >= tickets) {
                    int newAvailable = show.getAvailableTickets() - tickets;
                    int newBooked = show.getBookedTickets() + tickets;

                    show.setAvailableTickets(newAvailable);
                    show.setBookedTickets(newBooked);

                    System.out.println(tickets
                            + " tickets successfully booked for " + showTime);
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
     * Function to cancel the previously booked tickets for a certain
     * movie schedule.
     * @param showTime
     * @param tickets
     */
    public void cancelReservation(final String showTime, final int tickets) {
        for (Movie show : showTimes) {
            if (show.getTime().equals(showTime)) {
                if (tickets <= show.getBookedTickets()) {
                    int newAvailable = show.getAvailableTickets() + tickets;
                    int newBooked = show.getBookedTickets() - tickets;

                    show.setAvailableTickets(newAvailable);
                    show.setBookedTickets(newBooked);
                    System.out.println(tickets
                            + " tickets succesfully cancelled for " + showTime);
                } else {
                    System.out.println(
                            "Invalid operation (Attempt to cancel more tickets "
                            + "than booked)");
                }
                return;
            }
        }
    }
    /**
     * Function to display all the movie schedules and their available tickets.
     */
    public void displaySchedule() {
        System.out.println("\n--- Movie Schedule ---");
        for (Movie show : showTimes) {
            System.out.println("Time: " + show.getTime()
                    + " | Seats Available: " + show.getAvailableTickets());
        }
        System.out.println("\n----------------------");
    }

    /**
     * main function to test and run program.
     * @param args
     */
    public static void main(final String[] args) {
        MovieBookingSystem movie = new MovieBookingSystem();
        movie.displaySchedule();
        String time = "10:00 AM";
        String time2 = "1:00 PM";
        final int tickets = 5;
        final int tickets2 = 2;
        final int tickets3 = 3;
        final int over = 100;
        movie.bookTicket(time, tickets);
        movie.bookTicket(time, over);
        movie.cancelReservation(time, tickets3);
        movie.bookTicket(time2, tickets2);
        movie.cancelReservation(time, tickets);

    }
}
