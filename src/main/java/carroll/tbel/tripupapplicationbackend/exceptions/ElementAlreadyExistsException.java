package carroll.tbel.tripupapplicationbackend.exceptions;

public class ElementAlreadyExistsException extends RuntimeException{

    public ElementAlreadyExistsException() {
        super("The element already exists");
    }

    public ElementAlreadyExistsException(String message) {
        super(message);
    }
}
