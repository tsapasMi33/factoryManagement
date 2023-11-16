package be.tsapasmi.factorymanagement.bl.exceptions;

import be.tsapasmi.factorymanagement.domain.entities.Product;
import be.tsapasmi.factorymanagement.domain.enums.Step;

public class NotStartedStepException extends RuntimeException {
    public NotStartedStepException(Step targetStep, Product product) {
        super("Product with id [" + product.getId() + "] has not yet started being " + targetStep + "!");
    }
}
