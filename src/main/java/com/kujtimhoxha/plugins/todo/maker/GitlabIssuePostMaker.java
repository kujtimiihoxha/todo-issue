package com.kujtimhoxha.plugins.todo.maker;

import com.kujtimhoxha.plugins.todo.base.Maker;
import com.kujtimhoxha.plugins.todo.config.Configurations;
import com.kujtimhoxha.plugins.todo.exception.GitlabException;
import com.kujtimhoxha.plugins.todo.model.Issue;
import com.kujtimhoxha.plugins.todo.model.gitlab.GitLabMilestone;
import com.kujtimhoxha.plugins.todo.model.gitlab.GitlabIssuePost;
import com.kujtimhoxha.plugins.todo.http.GitlabConnector;

import java.io.File;
import java.io.IOException;

/**
 * GitlabIssuePostMaker.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class GitlabIssuePostMaker implements Maker<GitlabIssuePost> {
    @Override
    public final GitlabIssuePost make(final Issue issue,
                                      final File file,
                                      final Configurations conf)
            throws IOException, GitlabException {
        final GitlabIssuePost issuePost = new GitlabIssuePost();
        issuePost.setTitle(issue.getTitle());
        if (conf.isFileLink()) {
            final StringBuilder body =
                new StringBuilder(issue.getBody());
            final StringBuilder fileLink =
                new StringBuilder(conf.getGitlabUrl());
            fileLink.append(conf.getRepositoryUsername())
                    .append("/")
                    .append(conf.getRepository())
                    .append("/tree/master")
                    .append(
                        file.getPath()
                            .replace(System.getProperty("user.dir"), "")
                    );
            body.append("\n").append(
                String.format(
                    "```todo-issue``` File : [%s](%s), Line : [L%s](%s)",
                    file.getName(),
                    fileLink.toString(),
                    issue.getLineNumber(),
                    fileLink.append("#L")
                            .append(issue.getLineNumber())
                            .toString()
                )
            );
            issue.setBody(body.toString());
        }
        issuePost.setDescription(issue.getBody());
        if (issue.getAssignee() != null) {
            issuePost.setAssigneeId(
                new GitlabConnector()
                    .getUser(
                        conf,
                        issue.getAssignee()
                    ).getId()
            );
        }
        issuePost.setLabels(issue.getLabels());
        if (issue.getMilestone() != null) {
            final GitLabMilestone milestone =
                    new GitlabConnector()
                            .getMilestone(conf, issue.getMilestone());
            if (milestone != null) {
                issuePost.setMilestoneId(milestone.getId());
            }
        }
        return issuePost;
    }
}
