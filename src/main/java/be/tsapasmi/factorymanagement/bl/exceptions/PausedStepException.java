package be.tsapasmi.factorymanagement.bl.exceptions;

import be.tsapasmi.factorymanagement.domain.entities.Product;
import be.tsapasmi.factorymanagement.domain.enums.Step;

public class PausedStepException extends RuntimeException {
    public PausedStepException(Step targetStep, Product product) {
        super("Product with id [" + product.getId() + "] on " + targetStep + "is paused. Please continue first!");
    }
}
