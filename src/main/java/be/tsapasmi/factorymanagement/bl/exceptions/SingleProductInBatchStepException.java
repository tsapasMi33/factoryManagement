package be.tsapasmi.factorymanagement.bl.exceptions;

public class SingleProductInBatchStepException extends RuntimeException {
    public SingleProductInBatchStepException() {
        super("Single Product cannot be processed in this Step!");
    }
}
