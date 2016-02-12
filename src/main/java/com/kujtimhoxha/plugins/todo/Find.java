package com.kujtimhoxha.plugins.todo;

import com.kujtimhoxha.plugins.todo.client.GithubClient;
import com.kujtimhoxha.plugins.todo.client.GitlabClient;
import com.kujtimhoxha.plugins.todo.config.ConfigReader;
import com.kujtimhoxha.plugins.todo.config.Configurations;
import com.kujtimhoxha.plugins.todo.config.Gitserver;
import com.kujtimhoxha.plugins.todo.filter.TypeFilter;
import com.kujtimhoxha.plugins.todo.finder.FileFinder;
import com.kujtimhoxha.plugins.todo.finder.TodoFinder;
import com.kujtimhoxha.plugins.todo.logger.Logger;
import com.kujtimhoxha.plugins.todo.model.Todo;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Find goal.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version 1.0.0
 * @since 1.0.0
 */
@Mojo(name = "find", defaultPhase = LifecyclePhase.PROCESS_SOURCES)
public class Find  extends AbstractMojo {
    /**
     * List of folders/files to search for todo tags.
     */
    @Parameter(property = "sources", required = true)
    private List<File> sources;

    /**
     * List of folders/files to exclude from search.
     */
    @Parameter(property = "excludes")
    private List<File> excludes;

    /**
     * Configurations file contains source control details.
     */
    @Parameter(property = "config", defaultValue = "${basedir}/todo.json")
    private File config;

    /**
     * Types of files to use for search.
     */
    @Parameter(property = "types", defaultValue = ".java")
    private List<String> types;

    @Override
    public final void execute() throws MojoExecutionException,
            MojoFailureException {
        setLog(Logger.getlog());
        new TypeFilter(this.types);
        if (this.sources.size() < 1) {
            throw new MojoExecutionException(
                "There must be at least one source directory/file"
            );
        }
        if (!this.config.exists()) {
            throw new MojoExecutionException(
                String.format(
                    "Configurations file  '%s' does not exist",
                    config.getPath()
                )
            );
        }

        final Set<File> files =
                new FileFinder(
                        sources,
                        excludes,
                        types
                ).find();
        final Configurations conf;
        try {
            conf = ConfigReader.getConfig(config.getAbsolutePath());
        } catch (IOException e) {
            getLog().error(e.getMessage());
            throw new MojoExecutionException(e.getMessage());
        }
        if(files.size()==0){
            return;
        }
        List<Todo> todos = new TodoFinder(files).find();
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
