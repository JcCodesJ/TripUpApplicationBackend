package carroll.tbel.tripupapplicationbackend.exceptions;

public class ElementNotFoundException extends RuntimeException{

    public ElementNotFoundException() {
        super("The searched-for element was not found");
    }

}
