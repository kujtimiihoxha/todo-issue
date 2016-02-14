package com.kujtimhoxha.plugins.todo.model.github;

import com.google.api.client.util.Key;

/**
 * GithubError.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class GithubError {

    @Key("resource")
    private String resource;
    @Key("field")
    private String field;
    @Key("code")
    private String code;
    @Key("message")
    private String message;

    public String getResource() {
        return resource;
    }

    public String getField() {
        return field;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
