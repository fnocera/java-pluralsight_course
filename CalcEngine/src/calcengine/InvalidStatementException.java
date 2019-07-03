package calcengine;

public class InvalidStatementException extends Exception {
    private static final long serialVersionUID = 1L;

    public InvalidStatementException(String reason, String statement) {
        super(reason + ": " + statement);

    }

    public InvalidStatementException (String reason, String statement, Throwable cause) {
        super(reason + ": " + statement, cause);
    }
}