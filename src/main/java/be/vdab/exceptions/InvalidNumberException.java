package be.vdab.exceptions;

public class InvalidNumberException extends Exception {
    public InvalidNumberException() {
        System.out.println("Number of beers to order cannot be negative");
    }
}
