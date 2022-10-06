package model.exception;

public class InstallmentCalculationException extends RuntimeException{
    public InstallmentCalculationException() {
        super("Case not handled");
    }

    public InstallmentCalculationException(String message) {
        super(message);
    }
}
