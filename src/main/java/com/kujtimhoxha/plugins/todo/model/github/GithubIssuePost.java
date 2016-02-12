package com.kujtimhoxha.plugins.todo.model.github;

import com.google.api.client.util.Key;

/**
 * GithubIssuePost.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class GithubIssuePost {
    @Key("title")
    private String title;
    @Key("body")
    private String body;
    @Key("assignee")
    private String assignee;
    @Key("milestone")
    private String milestone;
    @Key("labels")
    private String[] labels;

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getAssignee() {
        return assignee;
    }

    public String getMilestone() {
        return milestone;
    }

    public String[] getLabels() {
        return labels;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public void setMilestone(String milestone) {
        this.milestone = milestone;
    }

    public void setLabels(String[]  labels) {
        this.labels = labels;
    }
}
