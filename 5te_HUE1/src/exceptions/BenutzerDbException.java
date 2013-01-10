package exceptions;

public class BenutzerDbException extends DbException {

    /**
     *
     */
    private static final long serialVersionUID = 2689027575217343875L;

    public BenutzerDbException(Exception e) {
        // TODO Auto-generated constructor stub
        super(e);
    }

    public BenutzerDbException(String string) {
        super(new Exception(string));
    }

}
