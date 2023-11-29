package be.tsapasmi.factorymanagement.bl.exceptions;

import be.tsapasmi.factorymanagement.domain.entities.Product;
import be.tsapasmi.factorymanagement.domain.enums.Step;

public class BadPathException extends RuntimeException {
    public BadPathException(Step targetStep, Product product) {
        super(targetStep.name() + " is not the next for product variant [" + product.getVariant().getCode() + "]. Please go to " + product.getNextStep() + " first!");
    }

    public BadPathException() {
        super("The product with this code is not at this step!");
    }
}
