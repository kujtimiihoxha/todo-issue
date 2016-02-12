package com.kujtimhoxha.plugins.http;

import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.kujtimhoxha.plugins.base.Connector;
import com.kujtimhoxha.plugins.config.Configurations;
import com.kujtimhoxha.plugins.exception.GitlabException;
import com.kujtimhoxha.plugins.model.gitlab.*;

import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;

/**
 * GitlabConnector.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@SuppressWarnings("Duplicates")
public class GitlabConnector
        implements Connector<GitLabIssueResponse, GitlabIssuePost> {
    /**
     * HTTP_TRANSPORT.
     */
    static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    /**
     * JSON_FACTORY.
     */
    static final JsonFactory JSON_FACTORY = new JacksonFactory();
    @Override
    public final List<GitLabIssueResponse> getIssues(
            final Configurations config)  throws GitlabException, IOException {
        HttpRequestFactory requestFactory =
            HTTP_TRANSPORT.createRequestFactory(
                new HttpRequestInitializer() {
                    @Override
                    public void initialize(final HttpRequest request) {
                        request.setParser(
                            new JsonObjectParser(JSON_FACTORY)
                        );
                    }
            });
        GitlabIssuesUrl url = new GitlabIssuesUrl(
                config.getGitlabUrl(),
                config.getRepository(),
                config.getRepositoryUsername()
        );
        GitLabIssueResponse[] issues;
        try {
            HttpRequest request = requestFactory.buildGetRequest(url);
            request.getHeaders().set("PRIVATE-TOKEN", config.getToken());
            try {
                issues = request.execute()
                        .parseAs(GitLabIssueResponse[].class);
            } catch (HttpResponseException e){
                throw new GitlabException(e.getContent());
            }
        }catch (IOException exp) {
            final IOException ioException = new IOException(
                    "Could not reach : " + exp.getMessage()
            );
            ioException.setStackTrace(exp.getStackTrace());
            throw ioException;
        }
        return Arrays.asList(issues);
    }
    @Override
    public final GitLabIssueResponse createIssue(final Configurations config,
                                           final GitlabIssuePost issue)
            throws GitlabException, IOException {
        HttpRequestFactory requestFactory =
            HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
                @Override
                public void initialize(final HttpRequest request) {
                    request.setParser(new JsonObjectParser(JSON_FACTORY));
                }
            });
        GitlabIssuesUrl url = new GitlabIssuesUrl(
                config.getGitlabUrl(),
                config.getRepository(),
                config.getRepositoryUsername()
        );
        JsonHttpContent content = new JsonHttpContent(
                new JacksonFactory(),
                issue
        );
        GitLabIssueResponse issueResponse;
        try {
            HttpRequest request = requestFactory.buildPostRequest(url, content);
            request.getHeaders().set("PRIVATE-TOKEN", config.getToken());
            try {
                issueResponse = request
                        .execute()
                        .parseAs(GitLabIssueResponse.class);
            } catch (HttpResponseException e){
                throw new GitlabException(e.getContent());
            }
        } catch (IOException exp) {
            final IOException ioException = new IOException(
                    "Could not reach : " + exp.getMessage()
            );
            ioException.setStackTrace(exp.getStackTrace());
            throw ioException;
        }
        return issueResponse;
    }

    /**
     * Gets the user by username.
     * @param config configuration file.
     * @param username username.
     * @return the gitlab user object.
     * @throws IOException if there is a problem with
     *  the connection.
     */
    public final GitLabUser getUser(final Configurations config,
                                    final String username)
            throws GitlabException, IOException {
        HttpRequestFactory requestFactory =
            HTTP_TRANSPORT.createRequestFactory(
                new HttpRequestInitializer() {
                    @Override
                    public void initialize(final HttpRequest request) {
                        request.setParser(
                            new JsonObjectParser(JSON_FACTORY)
                        );
                    }
            });
        GitlabAssigneeUrl url = new GitlabAssigneeUrl(
                config.getGitlabUrl()
        );
        url.set("username", username);
        GitLabUser gitLabUser;
        try {
            HttpRequest request = requestFactory.buildGetRequest(url);
            request.getHeaders().set("PRIVATE-TOKEN", config.getToken());
            try {
                gitLabUser = request.execute().parseAs(GitLabUser[].class)[0];
            } catch (HttpResponseException e){
                throw new GitlabException(e.getContent());
            }
        } catch (IOException exp) {
            final IOException ioException = new IOException(
                    "Could not reach : " + exp.getMessage()
            );
            ioException.setStackTrace(exp.getStackTrace());
            throw ioException;
        }

        return  gitLabUser;
    }

    /**
     * Gets milestone by title.
     * @param config   configuration file.
     * @param title    title of the milestone.
     * @return         the milestone model.
     * @throws IOException if there is a problem with
     *  the connection.
     */
    public final GitLabMilestone getMilestone(final Configurations config,
                                              final String title)
            throws GitlabException, IOException {
        HttpRequestFactory requestFactory =
            HTTP_TRANSPORT.createRequestFactory(
                new HttpRequestInitializer() {
                    @Override
                    public void initialize(final HttpRequest request) {
                        request.setParser(
                            new JsonObjectParser(JSON_FACTORY)
                        );
                    }
                }
            );
        GitlabMilestonesUrl url = new GitlabMilestonesUrl(
                config.getGitlabUrl(),
                config.getRepository(),
                config.getRepositoryUsername()
        );
       try {
           HttpRequest request = requestFactory.buildGetRequest(url);
           request.getHeaders().set("PRIVATE-TOKEN", config.getToken());
           try{
               GitLabMilestone[] milestones = request
                       .execute().parseAs(GitLabMilestone[].class);
               for (GitLabMilestone milestone : milestones) {
                   if (milestone.getTitle().equals(title)) {
                       return milestone;
                   }
               }
           } catch (HttpResponseException e){
               throw new GitlabException(e.getContent());
           }
       } catch (IOException exp) {
           final IOException ioException = new IOException(
                   "Could not reach : " + exp.getMessage()
           );
           ioException.setStackTrace(exp.getStackTrace());
           throw ioException;
       }

        return null;
    }
}
