package com.kujtimhoxha.plugins.todo.config;

import com.google.api.client.util.Key;

/**
 * Configurations.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Configurations {

    /**
     * Git Server.
     */
    @Key("git-server")
    private String gitServer;

    /**
     * Gitlab Url.
     */
    @Key("gitlab-url")
    private String gitlabUrl;

    /**
     * User that created the repository.
     */
    @Key("repository-username")
    private String repositoryUsername;

    /**
     * User who will write the issue.
     */
    @Key("issuer-username")
    private String issuerUsername;

    /**
     * Token.
     */
    @Key("token")
    private String token;

    /**
     * Repository name.
     */
    @Key("repository")
    private String repository;

    /**
     * File link.
     */
    @Key("file-link")
    private boolean fileLink = true;

    /**
     * Get git server.
     * @return gitserver name.
     */
    public final  String getGitServer() {
        return gitServer;
    }

    /**
     * Get gitlab url.
     * @return gitlab url.
     */
    public final String getGitlabUrl() {
        return gitlabUrl;
    }

    /**
     * Get user that created the repository.
     * @return user that created the repository.
     */
    public final String getRepositoryUsername() {
        return repositoryUsername;
    }

    /**
     * Get user who will write the issue.
     * @return user who will write the issue.
     */
    public final String getIssuerUsername() {
        return issuerUsername;
    }

    /**
     * Get private token.
     * @return  provate tken.
     */
    public final String getToken() {
        return token;
    }

    /**
     * Get repository name.
     * @return repository name.
     */
    public final String getRepository() {
        return repository;
    }
    /**
     * Is file link true.
     * @return fileLink boolean.
     */
    public final boolean isFileLink() {
        return fileLink;
    }
}
