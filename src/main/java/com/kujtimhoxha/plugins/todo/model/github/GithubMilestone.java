package com.kujtimhoxha.plugins.todo.model.github;

import com.google.api.client.util.DateTime;
import com.google.api.client.util.Key;

/**
 * GithubMilestone.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class GithubMilestone {
    @Key("url")
    private String url;
    @Key("html_url")
    private String htmlUrl;
    @Key("labels_url")
    private String labelsUrl;
    @Key("id")
    private Integer id;
    @Key("number")
    private Integer number;
    @Key("state")
    private String state;
    @Key("title")
    private String title;
    @Key("description")
    private String description;
    @Key("creator")
    private GithubUser creator;
    @Key("open_issues")
    private Integer openIssues;
    @Key("closed_issues")
    private Integer closedIssues;
    @Key("created_at")
    private DateTime createdAt;
    @Key("updated_at")
    private DateTime updatedAt;
    @Key("closed_at")
    private DateTime closedAt;
    @Key("due_on")
    private DateTime dueOn;

    public String getUrl() {
        return url;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getLabelsUrl() {
        return labelsUrl;
    }

    public Integer getId() {
        return id;
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

    public String getDescription() {
        return description;
    }

    public GithubUser getCreator() {
        return creator;
    }

    public Integer getOpenIssues() {
        return openIssues;
    }

    public Integer getClosedIssues() {
        return closedIssues;
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public DateTime getUpdatedAt() {
        return updatedAt;
    }

    public DateTime getClosedAt() {
        return closedAt;
    }

    public DateTime getDueOn() {
        return dueOn;
    }

    @Override
    public String toString() {
        return "GithubMilestone{" +
                "url='" + url + '\'' +
                ", htmlUrl='" + htmlUrl + '\'' +
                ", labelsUrl='" + labelsUrl + '\'' +
                ", id=" + id +
                ", number=" + number +
                ", state='" + state + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", creator=" + creator +
                ", openIssues=" + openIssues +
                ", closedIssues=" + closedIssues +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", closedAt=" + closedAt +
                ", dueOn=" + dueOn +
                '}';
    }
}
