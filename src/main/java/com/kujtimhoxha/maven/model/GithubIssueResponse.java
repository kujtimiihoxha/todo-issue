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
package com.kujtimhoxha.maven.model;

import com.google.api.client.util.DateTime;
import com.google.api.client.util.Key;

import java.util.ArrayList;
import java.util.List;

/**
 * GithubIssueResponse.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class GithubIssueResponse {
    @Key("id")
    private Integer id;
    @Key("url")
    private String url;
    @Key("repository_url")
    private String repositoryUrl;
    @Key("labels_url")
    private String labelsUrl;
    @Key("comments_url")
    private String commentsUrl;
    @Key("events_url")
    private String eventsUrl;
    @Key("html_url")
    private String htmlUrl;
    @Key("number")
    private Integer number;
    @Key("state")
    private String state;
    @Key("title")
    private String title;
    @Key("body")
    private String body;
    @Key("user")
    private GithubUser user;
    @Key("labels")
    private List<GithubLabel> labels = new ArrayList<GithubLabel>();
    @Key("assignee")
    private GithubUser assignee;
    @Key("milestone")
    private GithubMilestone milestone;
    @Key("locked")
    private Boolean locked;
    @Key("comments")
    private Integer comments;
    @Key("pull_request")
    private GithubPullRequest pullRequest;
    @Key("closed_at")
    private DateTime closedAt;
    @Key("created_at")
    private DateTime createdAt;
    @Key("updated_at")
    private DateTime updatedAt;

    public Integer getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getRepositoryUrl() {
        return repositoryUrl;
    }

    public String getLabelsUrl() {
        return labelsUrl;
    }

    public String getCommentsUrl() {
        return commentsUrl;
    }

    public String getEventsUrl() {
        return eventsUrl;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public Integer getNumber() {
        return number;
    }

    public String getState() {
        return state;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public GithubUser getUser() {
        return user;
    }

    public List<GithubLabel> getLabels() {
        return labels;
    }

    public GithubUser getAssignee() {
        return assignee;
    }

    public GithubMilestone getMilestone() {
        return milestone;
    }

    public Boolean getLocked() {
        return locked;
    }

    public Integer getComments() {
        return comments;
    }

    public GithubPullRequest getPullRequest() {
        return pullRequest;
    }

    public DateTime getClosedAt() {
        return closedAt;
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public DateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "GithubIssueResponse{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", repositoryUrl='" + repositoryUrl + '\'' +
                ", labelsUrl='" + labelsUrl + '\'' +
                ", commentsUrl='" + commentsUrl + '\'' +
                ", eventsUrl='" + eventsUrl + '\'' +
                ", htmlUrl='" + htmlUrl + '\'' +
                ", number=" + number +
                ", state='" + state + '\'' +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", user=" + user +
                ", labels=" + labels +
                ", assignee=" + assignee +
                ", milestone=" + milestone +
                ", locked=" + locked +
                ", comments=" + comments +
                ", pullRequest=" + pullRequest +
                ", closedAt=" + closedAt +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
