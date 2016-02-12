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
package com.kujtimhoxha.plugins.todo.finder;

import com.kujtimhoxha.plugins.todo.base.Finder;
import com.kujtimhoxha.plugins.todo.filter.ExcludedFilter;
import com.kujtimhoxha.plugins.todo.filter.TypeFilter;
import org.apache.maven.plugin.MojoExecutionException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * FileFinder.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class FileFinder implements Finder {
    /**
     * List of folders/files to search for todo tags.
     */
    private List<File> sources;

    /**
     * List of folders/files to exclude from search.
     */
    private List<File> excludes;

    /**
     * Types of files to use for search.
     */
    private List<String> types;

    /**
     * List of files to search for todo tags.
     */
    private static final List<File> FILES = new ArrayList<File>();

    /**
     * Ctr.
     * @param srcs sources.
     * @param exc excludes.
     * @param tp types.
     */
    public FileFinder(final List<File> srcs,
                      final List<File> exc,
                      final List<String> tp) {
        this.sources = srcs;
        this.excludes = exc;
        this.types = tp;
    }
    @Override
    public final List<File> find() throws MojoExecutionException {
        for (File source : this.sources) {
            if (!(source.exists())) {
                throw new MojoExecutionException(
                        String.format(
                                "Directory/File '%s' does not exist",
                                source.getPath()
                        )
                );
            }
            this.addFiles(source);
        }
        return FileFinder.FILES;
    }

    /**
     * Adds files from directory and subdirectories if they are not excluded.
     * @param source source directory.
     * @throws MojoExecutionException if something goes wrong.
     */
    public final void addFiles(final File source) throws
            MojoExecutionException {
        final List<File> folders = new ArrayList<File>();
        final List<File> files = new ArrayList<File>();
        for (final File exclude : this.excludes) {
            if (exclude.exists()) {
                if (exclude.isDirectory()) {
                    folders.add(exclude);
                } else if (exclude.isFile()) {
                    files.add(exclude);
                }
            }
        }
        if (source.isFile()) {
            if (new TypeFilter(types)
                    .accept(source, source.getName())) {
                FileFinder.FILES.add(source);
            }
        } else {
            File[] subFiles = source.listFiles();
            if (subFiles == null) {
                subFiles = new File[0];
            }
            for (File file : subFiles) {
                if (file.isFile()) {
                    if (new ExcludedFilter(files)
                            .accept(file, file.getName())
                            && new TypeFilter(types)
                            .accept(file, file.getName())) {
                            FileFinder.FILES.add(file);
                    }
                } else if (file.isDirectory() && new ExcludedFilter(folders)
                        .accept(file, file.getName())) {
                    addFiles(file);
                }
            }
        }
    }
}
