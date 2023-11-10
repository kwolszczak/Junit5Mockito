package pl.kwolszczak.userservice.exceptions;

public class EmailServiceException extends RuntimeException {
    public EmailServiceException(String message) {
        super(message);
    }
}
