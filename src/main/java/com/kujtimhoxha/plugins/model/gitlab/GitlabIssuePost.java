package com.kujtimhoxha.plugins.model.gitlab;

import com.google.api.client.util.Key;

/**
 * GitlabIssuePost.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class GitlabIssuePost {
    @Key("id")
    private Integer id;
    @Key("title")
    private String title;
    @Key("description")
    private String description;
    @Key("assignee_id")
    private Integer assigneeId;
    @Key("milestone_id")
    private String milestoneId;
    @Key("labels")
    private String labels ;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(Integer assigneeId) {
        this.assigneeId = assigneeId;
    }

    public String getMilestoneId() {
        return milestoneId;
    }

    public void setMilestoneId(String milestoneId) {
        this.milestoneId = milestoneId;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }
}
