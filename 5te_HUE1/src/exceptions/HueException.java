package exceptions;

public class HueException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 5620744621930177824L;

    public HueException(Exception e) {
        super(e);
    }

}
