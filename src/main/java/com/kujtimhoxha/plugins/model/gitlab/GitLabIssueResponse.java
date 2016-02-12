package com.kujtimhoxha.plugins.model.gitlab;

import com.google.api.client.util.DateTime;
import com.google.api.client.util.Key;

/**
 * GitLabIssueResponse.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class GitLabIssueResponse {
    @Key("project_id")
    private Integer projectId;
    @Key("milestone")
    private GitLabMilestone milestone;
    @Key("author")
    private GitLabUser author;
    @Key("description")
    private String description;
    @Key("state")
    private String state;
    @Key("iid")
    private Integer iid;
    @Key("assignee")
    private GitLabUser assignee;
    @Key("labels")
    private String[] labels;
    @Key("id")
    private Integer id;
    @Key("title")
    private String title;
    @Key("updated_at")
    private DateTime updatedAt;
    @Key("created_at")
    private DateTime createdAt;

    public Integer getProjectId() {
        return projectId;
    }

    public GitLabMilestone getMilestone() {
        return milestone;
    }

    public GitLabUser getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public String getState() {
        return state;
    }

    public Integer getIid() {
        return iid;
    }

    public GitLabUser getAssignee() {
        return assignee;
    }

    public String[] getLabels() {
        return labels;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public DateTime getUpdatedAt() {
        return updatedAt;
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }
}
