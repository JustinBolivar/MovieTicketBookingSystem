package com.ticket;

public class Movie {
    /**
     * Field used to store the time of a movie schedule.
     */
    private String time;
    /**
     * Field used to store the available tickets per movie schedule.
     */
    private int availableTickets;
    /**
     * Field used to store the amount of tickets books to prevent canceling more
     * tickets than booked.
     */
    private int bookedTickets;

    /**
     * Constructor of the Movie class used to set the values of the fields.
     * 
     * @param times
     * @param availableTicket
     */
    public Movie(final String times, final int availableTicket) {
        super();
        this.time = times;
        this.availableTickets = availableTicket;
        this.bookedTickets = 0;
    }

    /**
     * getter for the time field.
     * 
     * @return time
     */
    public String getTime() {
        return time;
    }

    /**
     * getter for the availableTickets field.
     * 
     * @return availableTickets
     */
    public int getAvailableTickets() {
        return availableTickets;
    }

    public void setAvailableTickets(int availableTickets) {
        this.availableTickets = availableTickets;
    }

    /**
     * getter for the bookedTickets field.
     * 
     * @return bookedTickets
     */
    public int getBookedTickets() {
        return bookedTickets;
    }

    public void setBookedTickets(int bookedTickets) {
        this.bookedTickets = bookedTickets;
    }
}
