package com.kujtimhoxha.plugins.todo.http;

import com.google.api.client.http.GenericUrl;

/**
 * GithubIssuesUrl.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class GithubIssuesUrl extends GenericUrl {
    /**
     * Ctr.
     * @param repository    github repository name.
     * @param user          github repository creator.
     */
    public GithubIssuesUrl(final String repository,
                           final String user) {
        super(
            String.format(
                "https://api.github.com/repos/%s/%s/issues",
                user,
                repository
            )
        );
    }
}
