package org.pavan.productservice.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resource, Object id) {
        super(resource + " with ID " + id + " not found");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
