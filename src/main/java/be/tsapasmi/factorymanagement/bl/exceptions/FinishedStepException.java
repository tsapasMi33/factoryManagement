package be.tsapasmi.factorymanagement.bl.exceptions;

import be.tsapasmi.factorymanagement.domain.entities.Product;
import be.tsapasmi.factorymanagement.domain.enums.Step;

public class FinishedStepException extends RuntimeException {
    public FinishedStepException(Step targetStep, Product product) {
        super("Product with id [" + product.getId() + "] has already been " + targetStep + "!");
    }
}
