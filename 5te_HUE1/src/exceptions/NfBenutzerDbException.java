package exceptions;

import exceptions.BenutzerDbException;

public class NfBenutzerDbException extends BenutzerDbException {

    public NfBenutzerDbException(Exception e) {
        super(e);
        // TODO
    }

    public NfBenutzerDbException() {
        super("Not Found");
    }


}
