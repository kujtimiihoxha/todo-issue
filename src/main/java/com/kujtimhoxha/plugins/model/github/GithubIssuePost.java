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

import com.google.api.client.util.Key;

import java.util.ArrayList;
import java.util.List;

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
    private Integer milestone;
    @Key("labels")
    private List<String> labels = new ArrayList<String>();

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getAssignee() {
        return assignee;
    }

    public Integer getMilestone() {
        return milestone;
    }

    public List<String> getLabels() {
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

    public void setMilestone(Integer milestone) {
        this.milestone = milestone;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }
}
