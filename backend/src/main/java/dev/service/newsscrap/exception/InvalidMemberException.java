package dev.service.newsscrap.exception;

public class InvalidMemberException extends RuntimeException{
    public InvalidMemberException (String message) {
        super(message);
    }
}
