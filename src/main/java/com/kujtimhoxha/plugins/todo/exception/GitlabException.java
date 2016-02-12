package com.kujtimhoxha.plugins.todo.exception;

/**
 * GitlabException.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class GitlabException extends Exception {
    /**
     * Ctr.
     *
     * @param error error response.
     */
    public GitlabException(final String error) {
        super(error);
    }
}
