package com.kujtimhoxha.plugins.todo.finder;

import com.kujtimhoxha.plugins.todo.base.Finder;
import com.kujtimhoxha.plugins.todo.filter.ExcludedFilter;
import com.kujtimhoxha.plugins.todo.filter.TypeFilter;
import org.apache.maven.plugin.MojoExecutionException;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private List<File> files = new ArrayList<File>();

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
    public final Set<File> find() throws MojoExecutionException {
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
        return new HashSet<File>(this.files);
    }

    /**
     * Adds files from directory and subdirectories if they are not excluded.
     * @param source source directory.
     * @throws MojoExecutionException if something goes wrong.
     */
    public final void addFiles(final File source) throws
            MojoExecutionException {
        final List<File> folders = new ArrayList<File>();
        final List<File> excluded = new ArrayList<File>();
        for (final File exclude : this.excludes) {
            if (exclude.exists()) {
                if (exclude.isDirectory()) {
                    folders.add(exclude);
                } else if (exclude.isFile()) {
                    excluded.add(exclude);
                }
            }
        }
        if (source.isFile()) {
            if (new TypeFilter(types)
                    .accept(source, source.getName())) {
                this.files.add(source);
            }
        } else {
            File[] subFiles = source.listFiles();
            if (subFiles == null) {
                subFiles = new File[0];
            }
            for (File file : subFiles) {
                if (file.isFile()) {
                    if (new ExcludedFilter(excluded)
                            .accept(file, file.getName())
                            && new TypeFilter(types)
                            .accept(file, file.getName())) {
                        this.files.add(file);
                    }
                } else if (file.isDirectory() && new ExcludedFilter(folders)
                        .accept(file, file.getName())) {
                    addFiles(file);
                }
            }
        }
    }
}
