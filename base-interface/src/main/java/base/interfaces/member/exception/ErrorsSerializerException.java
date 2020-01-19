package base.interfaces.member.exception;

public class ErrorsSerializerException extends RuntimeException {

    private static final String message = "Fail Errors Object Json Serialize error : ";

    public ErrorsSerializerException(Exception e) {
        super(message+e.getMessage());
    }
}
