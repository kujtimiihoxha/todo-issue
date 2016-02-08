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
    private  int iid;
    @Key("id")
    private int id;
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

    public int getIid() {
        return iid;
    }

    public int getId() {
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
