package com.ticket;

public class Movie {
    String time;
    int availableTickets;
    int bookedTickets;

    public Movie(String time, int availableTickets) {
        super();
        this.time = time;
        this.availableTickets = availableTickets;
        this.bookedTickets = 0;
    }
}
