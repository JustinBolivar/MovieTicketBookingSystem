package com.ticket;

public abstract class BookingSystem {
    public abstract void checkAvailability(String showTime);

    public abstract void bookTicket(String showTime, int tickets);

    public abstract void cancelReservation(String showTime, int tickets);

}
