package com.kujtimhoxha.plugins.finder;

import com.kujtimhoxha.plugins.base.Finder;
import com.kujtimhoxha.plugins.client.GithubClient;
import com.kujtimhoxha.plugins.client.GitlabClient;
import com.kujtimhoxha.plugins.config.Configurations;
import com.kujtimhoxha.plugins.config.Gitserver;
import com.kujtimhoxha.plugins.logger.Logger;
import com.kujtimhoxha.plugins.matcher.TodoMatcher;
import com.kujtimhoxha.plugins.model.Issue;
import com.kujtimhoxha.plugins.model.Todo;
import org.apache.maven.plugin.MojoExecutionException;
import org.yaml.snakeyaml.constructor.ConstructorException;
import org.yaml.snakeyaml.scanner.ScannerException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * TodoFinder.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class TodoFinder implements Finder {
    @Override
    public final void find(final List<File> files, final Configurations conf)
            throws MojoExecutionException {
        final List<Todo> todos = new ArrayList<Todo>();
        for (File file : files) {
            try {
                for (final Issue issue : new TodoMatcher().match(file)) {
                    todos.add(new Todo(file, issue));
                }
            } catch (IOException exp) {
                final MojoExecutionException exception =
                    new MojoExecutionException(exp.getMessage());
                exception.setStackTrace(exception.getStackTrace());
                throw exception;
            } catch (ScannerException exp) {
                final MojoExecutionException exception =
                    new MojoExecutionException(
                        "Could not parse yaml : " + exp.getMessage()
                    );
                exception.setStackTrace(exception.getStackTrace());
                throw exception;
            } catch (ConstructorException exp) {
                final MojoExecutionException exception =
                    new MojoExecutionException(
                        "Could not construct object : " + exp.getMessage()
                    );
                exception.setStackTrace(exception.getStackTrace());
                throw exception;
            }
        }
        if (todos.isEmpty()) {
            Logger.getlog().info("No todos were found");
            return;
        }
        if (conf.getGitServer().equals(Gitserver.Github.toString())) {
            try {
                new GithubClient().run(todos, conf);
            } catch (Exception exp) {
                final MojoExecutionException exception =
                        new MojoExecutionException(exp.getMessage());
                exception.setStackTrace(exception.getStackTrace());
                throw exception;
            }
        } else if (conf.getGitServer().equals(Gitserver.Gitlab.toString())) {
            try {
                new GitlabClient().run(todos, conf);
            } catch (Exception exp) {
                final MojoExecutionException exception =
                        new MojoExecutionException(exp.getMessage());
                exception.setStackTrace(exception.getStackTrace());
                throw exception;
            }
        } else {
            throw new MojoExecutionException("Unknown git-server");
        }
    }
}
