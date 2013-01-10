package exceptions;

public class ReiseveranstaltungDbException extends DbException {

    /**
     *
     */
    private static final long serialVersionUID = 2689027575217343875L;

    public ReiseveranstaltungDbException(Exception e) {
        // TODO Auto-generated constructor stub
        super(e);
    }

    public ReiseveranstaltungDbException(String string) {
        super(new Exception(string));
    }

}
