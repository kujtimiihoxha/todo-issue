package com.kujtimhoxha.plugins.todo.http;

import com.google.api.client.http.HttpResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.kujtimhoxha.plugins.todo.base.Connector;
import com.kujtimhoxha.plugins.todo.config.Configurations;
import com.kujtimhoxha.plugins.todo.exception.GithubException;
import com.kujtimhoxha.plugins.todo.model.github.GithubErrorResponse;
import com.kujtimhoxha.plugins.todo.model.github.GithubIssuePost;
import com.kujtimhoxha.plugins.todo.model.github.GithubIssueResponse;
import com.kujtimhoxha.plugins.todo.model.github.GithubMilestone;

import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;

/**
 * GithubConnector.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class GithubConnector
        implements Connector<GithubIssueResponse, GithubIssuePost> {
    /**
     * HTTP_TRANSPORT.
     */
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    /**
     * JSON_FACTORY.
     */
    private static final JsonFactory JSON_FACTORY = new JacksonFactory();
    @Override
    public final List<GithubIssueResponse> getIssues(
            final Configurations config) throws GithubException, IOException {
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
        GithubIssuesUrl url = new GithubIssuesUrl(
                config.getRepository(),
                config.getRepositoryUsername()
        );
        url.set("access_token", config.getToken());
        url.set("state", "all");
        GithubIssueResponse[] issues;
        try {
            HttpRequest request = requestFactory.buildGetRequest(url);
            try {
                issues = request
                        .execute().parseAs(GithubIssueResponse[].class);
            } catch (HttpResponseException e) {
                final GithubErrorResponse response =
                        new JsonObjectParser(JSON_FACTORY)
                        .parseAndClose(
                                new StringReader(e.getContent()),
                                GithubErrorResponse.class
                        );
                throw new GithubException(response, e.getStatusCode());
            }
        } catch (IOException exp) {
            final IOException ioException = new IOException(
                    "Could not reach : " + exp.getMessage()
            );
            ioException.setStackTrace(exp.getStackTrace());
            throw ioException;
        }

        return Arrays.asList(issues);
    }

    @Override
    public final GithubIssueResponse createIssue(final Configurations config,
                                           final GithubIssuePost issue)
            throws GithubException, IOException {
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
        GithubIssuesUrl url = new GithubIssuesUrl(
                config.getRepository(),
                config.getRepositoryUsername()
        );
        url.set("access_token", config.getToken());
        JsonHttpContent content = new JsonHttpContent(
                new JacksonFactory(),
                issue
        );
        GithubIssueResponse issueResponse;
        try {
            HttpRequest request = requestFactory.buildPostRequest(url, content);
            try {
                issueResponse = request
                        .execute()
                        .parseAs(GithubIssueResponse.class);
            } catch (HttpResponseException e) {
                final GithubErrorResponse response =
                    new JsonObjectParser(JSON_FACTORY)
                        .parseAndClose(
                            new StringReader(e.getContent()),
                            GithubErrorResponse.class
                        );
                throw new GithubException(response, e.getStatusCode());
            }
        } catch (IOException exp) {
            final IOException ioException = new IOException(
                    "Could not reach : " + exp.getMessage()
            );
            ioException.setStackTrace(exp.getStackTrace());
            throw ioException;
        }
        return  issueResponse;
    }

    /**
     * Gets milestone by title.
     * @param config   configuration file.
     * @param title    title of the milestone.
     * @return         the milestone model.
     * @throws IOException if there is a problem with
     *  the connection.
     * @throws GithubException if there is a problem with
     *  the connection.
     */
    public final GithubMilestone getMilestone(final Configurations config,
                                              final String title)
            throws GithubException, IOException {
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
        GithubMilestonesUrl url = new GithubMilestonesUrl(
                config.getRepository(),
                config.getRepositoryUsername()
        );
        try {
            HttpRequest request = requestFactory.buildGetRequest(url);
            request.getHeaders().set("PRIVATE-TOKEN", config.getToken());
            try {
                GithubMilestone[] milestones = request
                        .execute().parseAs(GithubMilestone[].class);
                for (GithubMilestone milestone : milestones) {
                    if (milestone.getTitle().equals(title)) {
                        return milestone;
                    }
                }
            } catch (HttpResponseException e) {
                final GithubErrorResponse response =
                        new JsonObjectParser(JSON_FACTORY)
                                .parseAndClose(
                                        new StringReader(e.getContent()),
                                        GithubErrorResponse.class
                                );
                throw new GithubException(response, e.getStatusCode());
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
