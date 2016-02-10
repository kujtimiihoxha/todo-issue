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
package com.kujtimhoxha.plugins.http;

import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.kujtimhoxha.plugins.base.Connector;
import com.kujtimhoxha.plugins.config.Configurations;
import com.kujtimhoxha.plugins.model.gitlab.GitLabIssueResponse;
import com.kujtimhoxha.plugins.model.gitlab.GitLabMilestone;
import com.kujtimhoxha.plugins.model.gitlab.GitLabUser;
import com.kujtimhoxha.plugins.model.gitlab.GitlabIssuePost;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * GitlabConnector.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class GitlabConnector  implements Connector<GitLabIssueResponse,GitlabIssuePost> {
    static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    static final JsonFactory JSON_FACTORY = new JacksonFactory();
    @Override
    public List<GitLabIssueResponse> getIssues(Configurations config) throws IOException {
        HttpRequestFactory requestFactory =
                HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
                    @Override
                    public void initialize(HttpRequest request) {
                        request.setParser(new JsonObjectParser(JSON_FACTORY));
                    }
                });
        GitlabIssuesUrl url=new GitlabIssuesUrl(config.getGitlabUrl(),config.getRepository(),config.getRepositoryUsername());
        HttpRequest request = requestFactory.buildGetRequest(url);
        request.getHeaders().set("PRIVATE-TOKEN",config.getToken());
        GitLabIssueResponse[] issues=request.execute().parseAs(GitLabIssueResponse[].class);
        return Arrays.asList(issues);
    }

    @Override
    public GitLabIssueResponse createIssue(Configurations config, GitlabIssuePost issue) throws IOException {
        HttpRequestFactory requestFactory =
                HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
                    @Override
                    public void initialize(HttpRequest request) {
                        request.setParser(new JsonObjectParser(JSON_FACTORY));
                    }
                });
        GitlabIssuesUrl url=new GitlabIssuesUrl(config.getGitlabUrl(),config.getRepository(),config.getRepositoryUsername());
        JsonHttpContent content = new JsonHttpContent(new JacksonFactory(), issue);
        HttpRequest request = requestFactory.buildPostRequest(url, content);
        request.getHeaders().set("PRIVATE-TOKEN",config.getToken());
        return  request.execute().parseAs(GitLabIssueResponse.class);
    }

    public GitLabUser getUser(Configurations config, String username) throws IOException {
        HttpRequestFactory requestFactory =
                HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
                    @Override
                    public void initialize(HttpRequest request) {
                        request.setParser(new JsonObjectParser(JSON_FACTORY));
                    }
                });
        GitlabAssigneeUrl url=new GitlabAssigneeUrl(config.getGitlabUrl());
        url.set("username",username);
        HttpRequest request = requestFactory.buildGetRequest(url);
        request.getHeaders().set("PRIVATE-TOKEN",config.getToken());
        return  request.execute().parseAs(GitLabUser[].class)[0];
    }
    public GitLabMilestone getMilestone(Configurations config, String title) throws IOException {
        HttpRequestFactory requestFactory =
                HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
                    @Override
                    public void initialize(HttpRequest request) {
                        request.setParser(new JsonObjectParser(JSON_FACTORY));
                    }
                });
        GitlabMilestonesUrl url=new GitlabMilestonesUrl(config.getGitlabUrl(),config.getRepository(),config.getRepositoryUsername());
        HttpRequest request = requestFactory.buildGetRequest(url);
        request.getHeaders().set("PRIVATE-TOKEN",config.getToken());
        GitLabMilestone[] milestones=request.execute().parseAs(GitLabMilestone[].class);
        for(GitLabMilestone milestone: milestones){
            if(milestone.getTitle().equals(title)){
                return milestone;
            }
        }
        return null;
    }
}
