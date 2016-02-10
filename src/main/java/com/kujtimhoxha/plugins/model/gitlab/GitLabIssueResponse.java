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
