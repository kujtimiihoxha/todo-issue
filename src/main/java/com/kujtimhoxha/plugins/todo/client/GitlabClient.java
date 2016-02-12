package com.kujtimhoxha.plugins.todo.client;

import com.kujtimhoxha.plugins.todo.base.Client;
import com.kujtimhoxha.plugins.todo.config.Configurations;
import com.kujtimhoxha.plugins.todo.http.GitlabConnector;
import com.kujtimhoxha.plugins.todo.logger.Logger;
import com.kujtimhoxha.plugins.todo.maker.GitlabIssuePostMaker;
import com.kujtimhoxha.plugins.todo.model.Todo;
import com.kujtimhoxha.plugins.todo.model.gitlab.GitLabIssueResponse;
import com.kujtimhoxha.plugins.todo.model.gitlab.GitlabIssuePost;
import com.kujtimhoxha.plugins.todo.modifier.GitlabTodoModifier;
import java.util.List;

/**
 * GitlabClient.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class GitlabClient implements Client {

    @Override
    public final void run(final List<Todo> todos,
                          final Configurations conf)
            throws Exception {
        new GitlabTodoModifier().removeClosed(todos, conf);
        for (final Todo todo : todos) {
            if (todo.getIssue().getId() == null) {
                if (todo.getIssue().getTitle() == null) {
                    throw new Exception(
                        String.format(
                            "Title of issue in file '%s' is required",
                            todo.getFile().getAbsolutePath()
                        )
                    );
                }
                if (todo.getIssue().getBody() == null) {
                    throw new Exception(
                        String.format(
                            "Body of issue in file '%s' is required",
                            todo.getFile().getAbsolutePath()
                        )
                    );
                }
                Logger.getlog().info(
                    String.format(
                        "Found new todo '%s' in file '%s' at line '%s'",
                        todo.getIssue().getTitle(),
                        todo.getFile().getAbsolutePath(),
                        todo.getIssue().getLineNumber()
                    )
                );
                final GitlabIssuePost issue = new GitlabIssuePostMaker()
                        .make(todo.getIssue(), todo.getFile(), conf);
                final GitLabIssueResponse response =
                        new GitlabConnector().createIssue(conf, issue);
                new GitlabTodoModifier().issueCreated(response, todo);
            }
        }
    }


}
