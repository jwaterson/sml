package sml.exceptions;

import java.io.Serial;

public class IllegalInstructionException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 4L;

    public IllegalInstructionException() {
        super();
    }

    public IllegalInstructionException(String message) {
        super(message);
    }

}
