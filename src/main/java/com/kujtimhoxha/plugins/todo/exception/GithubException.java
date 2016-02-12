package com.kujtimhoxha.plugins.todo.exception;

import com.kujtimhoxha.plugins.todo.model.github.GithubError;
import com.kujtimhoxha.plugins.todo.model.github.GithubErrorResponse;
import org.apache.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * GithubException.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class GithubException extends Exception {
    /**
     * Error message.
     */
    private StringBuilder message = new StringBuilder("");

    /**
     * Ctr.
     *
     * @param errorResponse response object.
     * @param code          http status code.
     */
    public GithubException(final GithubErrorResponse errorResponse,
                           final int code) {
        if (code == HttpStatus.SC_NOT_FOUND) {
            this.message.append("Repository or username not found");
        } else if (code == HttpStatus.SC_UNPROCESSABLE_ENTITY) {
            this.message.append(
                String.format(
                    "Received errors from github %s",
                    getErrorMessages(errorResponse)
                )
            );
        } else {
            this.message.append(
                String.format(
                    "Received errors from github %s",
                    errorResponse.getMessage()
                )
            );
        }
    }

    @Override
    public final String getMessage() {
        return this.message.toString();
    }

    /**
     * Gets error messages from response object.
     * @param errorResponse response object.
     * @return list of error messages
     */
    public final  List<String> getErrorMessages(
            final GithubErrorResponse errorResponse) {
        final List<String> messages = new ArrayList<String>();
        for (final GithubError error:errorResponse.getErrors()) {
            if (error.getCode().equals("missing")) {
                messages.add(
                    String.format(
                        "%s does not exist",
                        error.getResource()
                    )
                );
            } else if (error.getCode().equals("missing_field")) {
                messages.add(
                    String.format(
                        "%s is required",
                        error.getField()
                    )
                );
            } else if (error.getCode().equals("invalid")) {
                messages.add(
                    String.format(
                        "%s is not properly formatted",
                        error.getField()
                    )
                );
            } else if (error.getCode().equals("already_exists")) {
                messages.add(
                    String.format(
                        "%s already exists",
                        error.getResource()
                    )
                );
            } else {
                messages.add(
                    String.format(
                        "message %s , resource %s , field %s",
                        error.getMessage(),
                        error.getResource(),
                        error.getField()
                    )
                );
            }
        }
        return messages;
    }
}
