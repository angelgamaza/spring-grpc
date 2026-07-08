package com.gamaza.examples.grpc.server.constant;

/**
 * Constants for Exceptions
 */
public final class ExceptionConstants {

    /**
     * Global constants
     */
    // Global
    public static final String DESCRIPTION = "description";
    // Not found
    public static final String RESOURCE_NOT_FOUND = "Resource not found";
    public static final String RESOURCE_NOT_FOUND_DESCRIPTION = "The resource can not be found on the server";
    // Already exists
    public static final String RESOURCE_ALREADY_EXISTS = "Resource already exists";
    public static final String RESOURCE_ALREADY_EXISTS_DESCRIPTION = "The resource already exists on the server";
    // Illegal argument
    public static final String ILLEGAL_ARGUMENT = "Illegal argument received";
    public static final String ILLEGAL_ARGUMENT_DESCRIPTION = "The received argument is not valid";
    // Internal server error
    public static final String INTERNAL_SERVER_ERROR = "Internal server error";
    public static final String INTERNAL_SERVER_ERROR_DESCRIPTION = "Server response with an Internal Error";


    /**
     * Exception messages
     */
    // Not found
    public static final String NOT_FOUND_EXCEPTION_MESSAGE = "The object [%s] with parameters [%s] not found!";
    // Already exists
    public static final String ALREADY_EXISTS_EXCEPTION_MESSAGE = "The object [%s] with parameters [%s] already exists!";


    /**
     * Private constructor
     */
    private ExceptionConstants() {
        throw new IllegalStateException("Could not instantiate ExceptionConstants class!");
    }

}
