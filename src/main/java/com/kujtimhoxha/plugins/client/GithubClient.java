package com.kujtimhoxha.plugins.client;

import com.kujtimhoxha.plugins.base.Client;
import com.kujtimhoxha.plugins.config.Configurations;
import com.kujtimhoxha.plugins.exception.GithubException;
import com.kujtimhoxha.plugins.http.GithubConnector;
import com.kujtimhoxha.plugins.logger.Logger;
import com.kujtimhoxha.plugins.maker.GithubIssuePostMaker;
import com.kujtimhoxha.plugins.model.Todo;
import com.kujtimhoxha.plugins.model.github.GithubIssuePost;
import com.kujtimhoxha.plugins.model.github.GithubIssueResponse;
import com.kujtimhoxha.plugins.modifiers.GithubTodoModifier;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.regex.Pattern;

/**
 * GithubClient.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class GithubClient implements Client {

    @Override
    public final void run(final List<Todo> todos,
                          final Configurations conf)
            throws Exception {
        new GithubTodoModifier().removeClosed(todos, conf);
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
                final GithubIssuePost issue = new GithubIssuePostMaker()
                        .make(todo.getIssue(), todo.getFile(), conf);
                final GithubIssueResponse response =
                        new GithubConnector().createIssue(conf, issue);
                new GithubTodoModifier().issueCreated(response, todo);
            }
        }
    }


}
