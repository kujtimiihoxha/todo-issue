/**
 * Copyright (c) 2016 Kujtim Hoxha
 *
 * Permission is hereby granted, free of charge,
 * to any person obtaining a copy of this software
 * and associated documentation files (the "Software"),
 * to deal in the Software without restriction,
 * including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit
 * persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice
 * shall be included in all copies or substantial
 * portions of the Software.
 *
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
package com.kujtimhoxha.maven;

import com.kujtimhoxha.maven.validator.SourceValidator;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.nio.file.Paths;

/**
 * Find.
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@Mojo(name = "find", defaultPhase = LifecyclePhase.PROCESS_SOURCES)
public class Find extends AbstractMojo {

    /**
     * Source directory.
     */
    @Parameter(property = "source", required = true)
    private String source;

    /**
     * Excluded files or directories.
     */
    @Parameter(property = "excludes")
    private String[] excludes;

    /**
     * File types to search for todo's.
     */
    @Parameter(property = "types", defaultValue = ".java")
    private String[] types;

    /**
     * Base directory.
     */
    @Parameter(property = "base", defaultValue = "${basedir}")
    private String base;

    /**
     * Todo.json Path.
     */
    @Parameter(property = "config", defaultValue = "${basedir}/todo.json")
    private String config;

    /**
     * Execute.
     * @throws MojoExecutionException if there is an exception
     *  in executing the goal
     */
    public void execute() throws MojoExecutionException {
        if (!new SourceValidator().validate(Paths.get(base+this.source).toString())) {
            throw new MojoExecutionException(
                "Directory given does not exist or is wrong"
            );
        }
    }
}
