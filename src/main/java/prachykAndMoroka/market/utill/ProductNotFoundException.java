package prachykAndMoroka.market.utill;

public class ProductNotFoundException extends RuntimeException{
    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
