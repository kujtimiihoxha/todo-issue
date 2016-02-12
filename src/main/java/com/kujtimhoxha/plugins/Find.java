package com.kujtimhoxha.plugins;

import com.kujtimhoxha.plugins.config.ConfigReader;
import com.kujtimhoxha.plugins.config.Configurations;
import com.kujtimhoxha.plugins.filter.TypeFilter;
import com.kujtimhoxha.plugins.finder.TodoFinder;
import com.kujtimhoxha.plugins.logger.Logger;
import com.kujtimhoxha.plugins.filter.ExcludedFilter;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * List of files to search for todo tags.
     */
    private static final List<File> FILES = new ArrayList<File>();

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
        for (File source : this.sources) {
            if (!(source.exists())) {
                throw new MojoExecutionException(
                    String.format(
                        "Directory/File '%s' does not exist",
                        source.getPath()
                    )
                );
            }
            addFiles(source);
        }

        final Configurations conf;
        try {
            conf = ConfigReader.getConfig(config.getAbsolutePath());
        } catch (IOException e) {
            getLog().error(e.getMessage());
            throw new MojoExecutionException(e.getMessage());
        }
        try {
            new TodoFinder().find(FILES, conf);
        } catch (MojoExecutionException exp) {
            throw exp;
        }
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
                Find.FILES.add(source);
            }
        } else {
            File[] subFiles = source.listFiles();
            if (subFiles == null) {
                subFiles = new File[0];
            }
            for (File file : subFiles) {
                if (file.isFile()) {
                    if (new ExcludedFilter(files)
                            .accept(file, file.getName())) {
                        if (new TypeFilter(types)
                                .accept(source, source.getName())) {
                            Find.FILES.add(file);
                        }
                    }
                } else if (file.isDirectory() && new ExcludedFilter(folders)
                        .accept(file, file.getName())) {
                    addFiles(file);
                }
            }
        }
    }

}
