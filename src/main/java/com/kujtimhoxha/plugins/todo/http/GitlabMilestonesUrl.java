package com.kujtimhoxha.plugins.todo.http;

import com.google.api.client.http.GenericUrl;

/**
 * GitlabMilestonesUrl.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class GitlabMilestonesUrl extends GenericUrl {
    /**
     * Ctr.
     * @param gitlabUrl     gitlab server url.
     * @param repository    gitlab repository name.
     * @param user          gitlab repository creator.
     */
    public GitlabMilestonesUrl(final String gitlabUrl,
                               final String repository,
                               final String user) {
        super(
            String.format(
                "%s/api/v3/projects/%s%s/milestones",
                gitlabUrl,
                user,
                "%2F" + repository
            )
        );
    }
}
