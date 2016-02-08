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
package com.kujtimhoxha.plugins.model.github;

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
    private int id;
    @Key("number")
    private int number;
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

    public int getId() {
        return id;
    }

    public int getNumber() {
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
