package exceptions;

public class DbException extends HueException {

    /**
     *
     */
    private static final long serialVersionUID = 6378166969207746781L;

    public DbException(Exception e) {
        super(e);
    }

}
