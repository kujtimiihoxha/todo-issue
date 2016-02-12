package com.kujtimhoxha.plugins.todo.maker;

import com.kujtimhoxha.plugins.todo.base.Maker;
import com.kujtimhoxha.plugins.todo.config.Configurations;
import com.kujtimhoxha.plugins.todo.exception.GithubException;
import com.kujtimhoxha.plugins.todo.http.GithubConnector;
import com.kujtimhoxha.plugins.todo.model.Issue;
import com.kujtimhoxha.plugins.todo.model.github.GithubIssuePost;
import com.kujtimhoxha.plugins.todo.model.github.GithubMilestone;

import java.io.File;
import java.io.IOException;

/**
 * GithubIssuePostMaker.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class GithubIssuePostMaker implements Maker<GithubIssuePost> {
    @Override
    public final GithubIssuePost make(final Issue issue,
                                      final File file,
                                      final Configurations conf)
            throws IOException, GithubException {
        final GithubIssuePost issuePost = new GithubIssuePost();
        issuePost.setTitle(issue.getTitle());
        if (conf.isFileLink()) {
            final StringBuilder body =
                new StringBuilder(issue.getBody());
            final StringBuilder fileLink =
                new StringBuilder("https://github.com/");
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
        issuePost.setBody(issue.getBody());
        issuePost.setAssignee(issue.getAssignee());
        if (issue.getLabels() != null) {
            issuePost.setLabels(issue.getLabels().split(","));
        }
        if (issue.getMilestone() != null) {
            final GithubMilestone githubMilestone =
                    new GithubConnector()
                            .getMilestone(
                                    conf,
                                    issue.getMilestone()
                            );
            if (githubMilestone != null) {
                issuePost.setMilestone(
                    String.valueOf(githubMilestone.getId())
                );
            }
        }
        issuePost.setMilestone(issue.getMilestone());
        return issuePost;
    }
}
