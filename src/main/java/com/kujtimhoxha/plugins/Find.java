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
package com.kujtimhoxha.plugins;

import com.kujtimhoxha.plugins.clients.GitLabClient;
import com.kujtimhoxha.plugins.config.ConfigReader;
import com.kujtimhoxha.plugins.filter.FileFilter;
import com.kujtimhoxha.plugins.filter.FolderFilter;
import com.kujtimhoxha.plugins.clients.GithubClient;
import com.kujtimhoxha.plugins.validator.SourceValidator;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Find.
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@Mojo(name = "find", defaultPhase = LifecyclePhase.PROCESS_SOURCES)
public class Find extends AbstractMojo {
    /**
     * Files to search for todo's.
     */
    private final List<File> files=new ArrayList<File>();

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
        
        setLog(com.kujtimhoxha.plugins.logger.Log.getLog());
        if (!new SourceValidator().validate(Paths.get(base+this.source).toString())) {
            throw new MojoExecutionException(
                "Directory given does not exist or is wrong"
            );
        }
        addFiles(new File(Paths.get(base+this.source).toUri()));
        try {
            if(ConfigReader.getConfig(config).getGitServer().equals("Github")){
                getLog().info("Project uses  Github");
              try{
                  new GithubClient(files,types,config).run();
              }
              catch (MojoExecutionException e){
                  throw new MojoExecutionException(e.getMessage());
              }
            }
            else if((ConfigReader.getConfig(config).getGitServer().equals("Gitlab"))){
                getLog().info("Project uses  Gitlab");
                try{
                    new GitLabClient(files,types,config).run();
                }
                catch (MojoExecutionException e){
                    throw new MojoExecutionException(e.getMessage());
                }
            }

        } catch (IOException e) {
            throw new MojoExecutionException(
                    "Could not read config file, please add todo.json to the base directory"
            );
        }
    }

    /**
     * Adds files from directory and subdirectories if they are not excluded.
     * @param directory source directory.
     * @throws MojoExecutionException
     */
    public void addFiles(File directory) throws  MojoExecutionException{
        final List<String> folders=new ArrayList<String>();
        final List<String> files=new ArrayList<String>();
        for(String file : excludes){
            final File f=new File(file);
            if(f.exists()) {
                if (f.isDirectory()) {
                    folders.add(file);
                } else if (f.isFile()) {
                    files.add(file);
                }
            }
        }
        File[] fList = directory.listFiles();
        for (File file : fList != null ? fList : new File[0]) {
            if (file.isFile()) {
                if(new FileFilter(files).accept(file,file.getName())){
                    this.files.add(file);
                }
            } else if (file.isDirectory()) {
                if(new FolderFilter(folders).accept(file,file.getName())){
                    addFiles(file);
                }
            }
        }
    }

    /**
     * Gradle Adapter will be used for gradle tasks.
     */
    public void gradleAdapter(String source,String[] excludes,String[] types, String config){
        this.base=System.getProperty("user.dir");
        this.source=source;
        if(excludes==null) this.excludes=new String[0];
        else this.excludes=excludes;
        if(types==null) this.types= new String[]{".java"};
        else this.types=types;
        if(config==null) this.config= this.base+"/todo.json";
        else this.config=config;
    }
}
