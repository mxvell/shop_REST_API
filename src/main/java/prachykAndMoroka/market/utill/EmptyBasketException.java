package prachykAndMoroka.market.utill;

public class EmptyBasketException extends RuntimeException{
    public EmptyBasketException() {
    }

    public EmptyBasketException(String message) {
        super(message);
    }

    public EmptyBasketException(String message, Throwable cause) {
        super(message, cause);
    }
}
