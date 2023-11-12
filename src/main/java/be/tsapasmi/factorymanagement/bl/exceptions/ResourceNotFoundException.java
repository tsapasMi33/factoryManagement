package be.tsapasmi.factorymanagement.bl.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Long id, Class<?> resourceClass) {
        super(resourceClass.getSimpleName() + " with id " + id + " does not exist!");
    }
}
