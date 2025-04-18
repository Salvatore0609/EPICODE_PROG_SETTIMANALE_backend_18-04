package it.epicode.Gestione_viaggi_aziendali.common;

public class BookingConflictException extends RuntimeException {
    public BookingConflictException(String message) {
        super(message);
    }
}
