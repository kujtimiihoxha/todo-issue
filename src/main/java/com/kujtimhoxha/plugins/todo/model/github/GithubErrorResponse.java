package com.kujtimhoxha.plugins.todo.model.github;

import com.google.api.client.util.Key;

import java.util.ArrayList;
import java.util.List;

/**
 * GithubErrorResponse.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class GithubErrorResponse {

    @Key("message")
    private String message;
    @Key("errors")
    private List<GithubError> errors = new ArrayList<GithubError>();

    public String getMessage() {
        return message;
    }

    public List<GithubError> getErrors() {
        return errors;
    }
}
