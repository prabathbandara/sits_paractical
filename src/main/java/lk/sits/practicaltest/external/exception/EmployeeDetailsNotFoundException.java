package lk.sits.practicaltest.external.exception;

public class EmployeeDetailsNotFoundException extends RuntimeException {
    public EmployeeDetailsNotFoundException(String message) {
        super(message);
    }
}

