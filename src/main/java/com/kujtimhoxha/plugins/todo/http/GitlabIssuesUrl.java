package com.kujtimhoxha.plugins.todo.http;

import com.google.api.client.http.GenericUrl;

/**
 * GitlabIssuesUrl.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class GitlabIssuesUrl extends GenericUrl {

    /**
     * Ctr.
     * @param gitlabUrl     gitlab server url.
     * @param repository    gitlab repository name.
     * @param user          gitlab repository creator.
     */
    public GitlabIssuesUrl(final String gitlabUrl,
                           final String repository,
                           final String user) {
        super(
            String.format(
                "%s/api/v3/projects/%s%s/issues",
                gitlabUrl,
                user,
                "%2F" + repository
            )
        );
    }
}
