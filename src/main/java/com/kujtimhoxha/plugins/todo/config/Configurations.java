/**
 * Copyright (c) 2016 Kujtim Hoxha
 * <p>
 * Permission is hereby granted, free of charge,
 * to any person obtaining a copy of this software
 * and associated documentation files (the "Software"),
 * to deal in the Software without restriction,
 * including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit
 * persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice
 * shall be included in all copies or substantial
 * portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF
 * ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 * TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT
 * SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR
 * ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN
 * ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE
 * OR OTHER DEALINGS IN THE SOFTWARE.
 */
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
