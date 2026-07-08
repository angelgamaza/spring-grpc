package com.gamaza.examples.grpc.server.util;

import com.google.protobuf.Any;
import com.google.rpc.ErrorInfo;
import com.google.rpc.Status;
import io.grpc.protobuf.StatusProto;
import io.micrometer.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.gamaza.examples.grpc.server.constant.ExceptionConstants.*;
import static com.google.rpc.Code.*;

/**
 * Utils for exceptions
 */
public final class ExceptionUtils {

    // Logger
    private static final Logger logger = LoggerFactory.getLogger(ExceptionUtils.class);

    /**
     * Get the cause or the default message from the given {@link Throwable}
     *
     * @param throwable The {@link Throwable} instance
     * @return The first non-blank message or the class name if none found
     */
    public static String getCauseOrDefaultMessage(Throwable throwable) {
        if (throwable == null) {
            return null;
        }
        String message = ExceptionUtils.getFirstNonBlank(
                (throwable.getCause() != null) ? throwable.getCause().getLocalizedMessage() : null,
                throwable.getLocalizedMessage(),
                throwable.getMessage()
        );
        return message != null ? message : throwable.getClass().getSimpleName();
    }

    /**
     * Get the first non-blank string from the provided messages
     *
     * @param messages Varargs of messages
     * @return The first non-blank message or null if none found
     */
    private static String getFirstNonBlank(String... messages) {
        for (String message : messages) {
            if (!StringUtils.isBlank(message)) {
                return message;
            }
        }
        return null;
    }

    /**
     * Manage the Exceptions to throw with type NotFound
     *
     * @param objectName The object implied in the exception
     * @param parameters The exception message parameters
     * @return The built {@link Throwable}
     */
    public static Throwable manageNotFoundException(String objectName, String parameters) {
        // Build the error description
        String errorDescription = String.format(NOT_FOUND_EXCEPTION_MESSAGE, objectName, parameters);
        // Logs
        logger.error(errorDescription);
        // Error response
        return StatusProto.toStatusRuntimeException(
                Status
                        .newBuilder()
                        .setCode(NOT_FOUND.getNumber())
                        .setMessage(errorDescription)
                        .addDetails(Any.pack(ErrorInfo.newBuilder()
                                .setReason(RESOURCE_NOT_FOUND)
                                .putMetadata(DESCRIPTION, RESOURCE_NOT_FOUND_DESCRIPTION)
                                .build()))
                        .build()
        );
    }

    /**
     * Manage the Exceptions to throw with type AlreadyExists
     *
     * @param objectName The object implied in the exception
     * @param parameters The exception message parameters
     * @return The built {@link Throwable}
     */
    public static Throwable manageAlreadyExistsException(String objectName, String parameters) {
        // Build the error description
        String errorDescription = String.format(ALREADY_EXISTS_EXCEPTION_MESSAGE, objectName, parameters);
        // Logs
        logger.error(errorDescription);
        // Error response
        return StatusProto.toStatusRuntimeException(
                Status
                        .newBuilder()
                        .setCode(ALREADY_EXISTS.getNumber())
                        .setMessage(errorDescription)
                        .addDetails(Any.pack(ErrorInfo.newBuilder()
                                .setReason(RESOURCE_ALREADY_EXISTS)
                                .putMetadata(DESCRIPTION, RESOURCE_ALREADY_EXISTS_DESCRIPTION)
                                .build()))
                        .build()
        );
    }

    /**
     * Manage the Exceptions to throw with type IllegalArgument
     *
     * @param errorDescription The error description
     * @return The built {@link Throwable}
     */
    public static Throwable manageIllegalArgumentException(String errorDescription) {
        // Logs
        logger.error(errorDescription);
        // Error response
        return StatusProto.toStatusRuntimeException(
                Status
                        .newBuilder()
                        .setCode(INVALID_ARGUMENT.getNumber())
                        .setMessage(errorDescription)
                        .addDetails(Any.pack(ErrorInfo.newBuilder()
                                .setReason(ILLEGAL_ARGUMENT)
                                .putMetadata(DESCRIPTION, ILLEGAL_ARGUMENT_DESCRIPTION)
                                .build()))
                        .build()
        );
    }

    /**
     * Manage the Exceptions to throw with type Generic
     *
     * @param errorDescription The error description
     * @return The built {@link Throwable}
     */
    public static Throwable manageGenericException(String errorDescription) {
        // Logs
        logger.error(errorDescription);
        // Error response
        return StatusProto.toStatusRuntimeException(
                Status
                        .newBuilder()
                        .setCode(INTERNAL.getNumber())
                        .setMessage(errorDescription)
                        .addDetails(Any.pack(ErrorInfo.newBuilder()
                                .setReason(INTERNAL_SERVER_ERROR)
                                .putMetadata(DESCRIPTION, INTERNAL_SERVER_ERROR_DESCRIPTION)
                                .build()))
                        .build()
        );
    }

    /**
     * Private constructor to prevent instantiation
     */
    private ExceptionUtils() {
        throw new IllegalStateException("Could not instantiate ExceptionUtils class!");
    }

}