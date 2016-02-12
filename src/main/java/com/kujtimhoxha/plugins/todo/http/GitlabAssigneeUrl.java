package com.kujtimhoxha.plugins.todo.http;

import com.google.api.client.http.GenericUrl;

/**
 * GitlabAssigneeUrl.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class GitlabAssigneeUrl extends GenericUrl {
    /**
     * Ctr.
     * @param gitlabUrl url of the gitlab server.
     */
    public GitlabAssigneeUrl(final String gitlabUrl) {
        super(
            String.format(
                "%s/api/v3/users",
                gitlabUrl
            )
        );
    }
}
