package be.tsapasmi.factorymanagement.bl.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Object id, Class<?> resourceClass) {
        super(resourceClass.getSimpleName() + " with id " + id + " does not exist!");
    }
}
