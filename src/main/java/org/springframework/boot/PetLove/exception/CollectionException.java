package org.springframework.boot.PetLove.exception;

public class CollectionException extends Exception {
    private static final long serialVersionUID = 1L;

    public CollectionException(String message) {
        super(message);
    }

    public static String NotFoundException(String id) {
        return id+" not found!";
    }

    public static String AlreadyExists() {
        return "DatCho already exists";
    }
}
