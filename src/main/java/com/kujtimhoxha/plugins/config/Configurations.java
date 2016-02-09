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
package com.kujtimhoxha.plugins.config;

import com.google.api.client.util.Key;

/**
 * Configurations.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Configurations {
    @Key("git-server")
    private String gitServer;
    @Key("gitlab-url")
    private String gitlabUrl;
    @Key("type")
    private String type;
    @Key("repository-username")
    private String repositoryUsername;
    @Key("issuer-username")
    private String issuerUsername;
    @Key("token")
    private String token;
    @Key("repository")
    private String repository;
    @Key("project-id")
    private String projectId;


    public String getGitServer() {
        return gitServer;
    }

    public String getGitlabUrl() {
        return gitlabUrl;
    }

    public String getType() {
        return type;
    }

    public String getRepositoryUsername() {
        return repositoryUsername;
    }

    public String getIssuerUsername() {
        return issuerUsername;
    }

    public String getToken() {
        return token;
    }

    public String getRepository() {
        return repository;
    }

    public String getProjectId() {
        return projectId;
    }
}
