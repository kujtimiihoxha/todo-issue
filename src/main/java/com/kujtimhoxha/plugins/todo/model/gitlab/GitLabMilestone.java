package com.kujtimhoxha.plugins.todo.model.gitlab;

import com.google.api.client.util.DateTime;
import com.google.api.client.util.Key;

/**
 * GitLabMilestone.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class GitLabMilestone {
    @Key("due_date")
    private DateTime dueDate;
    @Key("project_id")
    private int projectId;
    @Key("state")
    private String state;
    @Key("description")
    private String description;
    @Key("iid")
    private  Integer iid;
    @Key("id")
    private Integer id;
    @Key("title")
    private String title;
    @Key("created_at")
    private DateTime createdAt;
    @Key("updated_at")
    private DateTime updatedAt;

    public DateTime getDueDate() {
        return dueDate;
    }

    public int getProjectId() {
        return projectId;
    }

    public String getState() {
        return state;
    }

    public String getDescription() {
        return description;
    }

    public Integer getIid() {
        return iid;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public DateTime getUpdatedAt() {
        return updatedAt;
    }
}
