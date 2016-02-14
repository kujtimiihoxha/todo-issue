package com.kujtimhoxha.plugins.todo.model.github;

import com.google.api.client.util.Key;

/**
 * GithubPullRequest.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class GithubPullRequest {
    @Key("url")
    private String url;
    @Key("html_url")
    private String htmlUrl;
    @Key("diff_url")
    private String diffUrl;
    @Key("patch_url")
    private String patchUrl;

    public String getUrl() {
        return url;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getDiffUrl() {
        return diffUrl;
    }

    public String getPatchUrl() {
        return patchUrl;
    }

    @Override
    public String toString() {
        return "GithubPullRequest{" +
                "url='" + url + '\'' +
                ", htmlUrl='" + htmlUrl + '\'' +
                ", diffUrl='" + diffUrl + '\'' +
                ", patchUrl='" + patchUrl + '\'' +
                '}';
    }
}
