package be.tsapasmi.factorymanagement.bl.exceptions;

public class BatchInSingleProductStepException extends RuntimeException {
    public BatchInSingleProductStepException() {
        super("Batch cannot be processed in this Step!");
    }
}
