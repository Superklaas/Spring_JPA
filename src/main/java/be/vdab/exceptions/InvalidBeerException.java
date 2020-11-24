package be.vdab.exceptions;

public class InvalidBeerException extends Exception {
    public InvalidBeerException() {
        System.out.println("Beer not in database");
    }
}
