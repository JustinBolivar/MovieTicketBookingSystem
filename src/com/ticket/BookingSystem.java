package com.ticket;

public abstract class BookingSystem {
    /**
     * abstract function used to check the availability of a specific schedule
     * of a movie.
     * @param showTime
     */
    public abstract void checkAvailability(String showTime);

    /**
     * abstract function that is used to book tickets on a specific movie
     * schedule.
     * @param showTime
     * @param tickets
     */
    public abstract void bookTicket(String showTime, int tickets);

    /**
     * abstract function that is used to cancel booked tickets for a specific
     * movie schedule.
     * @param showTime
     * @param tickets
     */
    public abstract void cancelReservation(String showTime, int tickets);

}
