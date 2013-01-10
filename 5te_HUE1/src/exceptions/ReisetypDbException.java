package exceptions;

public class ReisetypDbException extends DbException {

    /**
     *
     */
    private static final long serialVersionUID = 2689027575217343875L;

    public ReisetypDbException(Exception e) {
        // TODO Auto-generated constructor stub
        super(e);
    }

    public ReisetypDbException(String string) {
        super(new Exception(string));
    }

}
