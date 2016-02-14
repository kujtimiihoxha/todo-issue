package com.kujtimhoxha.plugins.todo.model.gitlab;

import com.google.api.client.util.Key;

import java.util.Map;

/**
 * GitlabErrorResponse.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class GitlabErrorResponse {
    @Key("message")
    private Map<String,String[]> message;

    public Map<String, String[]> getMessage() {
        return message;
    }
}
