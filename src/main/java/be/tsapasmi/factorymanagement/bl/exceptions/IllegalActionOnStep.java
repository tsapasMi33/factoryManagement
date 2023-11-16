package be.tsapasmi.factorymanagement.bl.exceptions;

import be.tsapasmi.factorymanagement.domain.enums.Step;

public class IllegalActionOnStep extends RuntimeException {
    public IllegalActionOnStep(Step targetStep, String action) {
        super(targetStep + "cannot be " + action + "!");
    }
}
