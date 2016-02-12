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
package com.kujtimhoxha.plugins.todo;

import com.kujtimhoxha.plugins.todo.client.GithubClient;
import com.kujtimhoxha.plugins.todo.client.GitlabClient;
import com.kujtimhoxha.plugins.todo.config.ConfigReader;
import com.kujtimhoxha.plugins.todo.config.Configurations;
import com.kujtimhoxha.plugins.todo.config.Gitserver;
import com.kujtimhoxha.plugins.todo.finder.FileFinder;
import com.kujtimhoxha.plugins.todo.finder.TodoFinder;
import com.kujtimhoxha.plugins.todo.logger.Logger;
import com.kujtimhoxha.plugins.todo.model.Todo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * GradleRunner.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class GradleRunner {
    /**
     * List of folders/files to search for todo tags.
     */
    private List<File> sources;

    /**
     * List of folders/files to exclude from search.
     */
    private List<File> excludes;

    /**
     * Configurations file contains source control details.
     */
    private File config;

    /**
     * Types of files to use for search.
     */
    private List<String> types;

    /**
     * Setup Ctr.
     *
     * @param srcs sources
     * @param exs excludes
     * @param conf config file
     * @param tps types
     * @throws Exception if something goes wrong.
     */
    public GradleRunner(final List<File> srcs,
                        final List<File> exs,
                        final File conf,
                        final List<String> tps)
            throws Exception {
        this.sources = srcs;
        if (this.sources == null || this.sources.size() < 1) {
            throw new Exception(
                "There must be at least one source directory/file"
            );
        }
        if (this.excludes == null) {
            this.excludes = new ArrayList<File>();
        } else {
            this.excludes = exs;
        }
        if (conf == null) {
            this.config = new File(
                System.getProperty("user.dir") + "/todo.json"
            );
        } else {
            this.config = conf;
        }
        if (tps == null || tps.size() == 0) {
            this.types = new ArrayList<String>();
            this.types.add(".java");
        } else {
            this.types = tps;
        }
    }

    /**
     * Sources Ctr.
     * @param src sources
     * @throws Exception if something goes wrong.
     */
    public GradleRunner(final List<File> src)
            throws Exception {
        this(
                src,
                new ArrayList<File>(),
                new File(
                    System.getProperty("user.dir") + "/todo.json"
                ),
                new ArrayList<String>() { { add(".java"); } }
        );
    }

    /**
     * Sources and Excludes Ctr.
     * @param src sources
     * @param exs excludes
     * @throws Exception if something goes wrong.
     */
    public GradleRunner(
            final List<File> src,
            final List<File> exs)
            throws Exception {
        this(
                src,
                exs,
                new File(
                    System.getProperty("user.dir") + "/todo.json"
                ),
                new ArrayList<String>() { { add(".java"); } }
        );
    }

    /**
     * Run method (equivalent of mojo execute).
     * @throws Exception if something goes wrong.
     */
    public final void run() throws Exception {
        if (!this.config.exists()) {
            throw new Exception(
            String.format(
                    "Configurations file  '%s' does not exist",
                    this.config.getPath()
                )
            );
        }

        final Set<File> files =
                new FileFinder(
                        this.sources,
                        this.excludes,
                        this.types
                ).find();
        final Configurations conf;
        try {
            conf = ConfigReader.getConfig(this.config.getAbsolutePath());
        } catch (IOException e) {
            Logger.getlog().error(e.getMessage());
            throw new Exception(e.getMessage());
        }
        if (files.size() == 0) {
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
                final Exception exception =
                        new Exception(exp.getMessage());
                exception.setStackTrace(exception.getStackTrace());
                throw exception;
            }
        } else if (conf.getGitServer().equals(Gitserver.Gitlab.toString())) {
            try {
                new GitlabClient().run(todos, conf);
            } catch (Exception exp) {
                final Exception exception =
                        new Exception(exp.getMessage());
                exception.setStackTrace(exception.getStackTrace());
                throw exception;
            }
        } else {
            throw new Exception("Unknown git-server");
        }
    }
}
