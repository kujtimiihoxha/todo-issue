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

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.kujtimhoxha.plugins.base.Connector;
import com.kujtimhoxha.plugins.config.Configurations;
import com.kujtimhoxha.plugins.model.github.GithubIssuePost;
import com.kujtimhoxha.plugins.model.github.GithubIssueResponse;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * GithubConnector.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class GithubConnector implements Connector<GithubIssueResponse,GithubIssuePost> {
    static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    static final JsonFactory JSON_FACTORY = new JacksonFactory();
    @Override
    public List<GithubIssueResponse> getIssues(Configurations config) throws IOException {
        HttpRequestFactory requestFactory =
                HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
                    @Override
                    public void initialize(HttpRequest request) {
                        request.setParser(new JsonObjectParser(JSON_FACTORY));
                    }
                });
        GithubIssuesUrl url=new GithubIssuesUrl(config.getRepository(),config.getRepositoryUsername());
        url.set("access_token",config.getToken());
        url.set("state","all");
        HttpRequest request = requestFactory.buildGetRequest(url);
        GithubIssueResponse[] issues=request.execute().parseAs(GithubIssueResponse[].class);
        return Arrays.asList(issues);
    }

    @Override
    public GithubIssueResponse createIssue(Configurations config, GithubIssuePost issue) throws IOException {
        HttpRequestFactory requestFactory =
                HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
                    @Override
                    public void initialize(HttpRequest request) {
                        request.setParser(new JsonObjectParser(JSON_FACTORY));
                    }
                });
        GithubIssuesUrl url=new GithubIssuesUrl(config.getRepository(),config.getRepositoryUsername());
        url.set("access_token",config.getToken());
        JsonHttpContent content = new JsonHttpContent(new JacksonFactory(), issue);
        HttpRequest request = requestFactory.buildPostRequest(url, content);
        return  request.execute().parseAs(GithubIssueResponse.class);
    }
}
