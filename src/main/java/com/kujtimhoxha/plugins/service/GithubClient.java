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
package com.kujtimhoxha.plugins.service;

import com.kujtimhoxha.plugins.base.Service;
import com.kujtimhoxha.plugins.config.ConfigReader;
import com.kujtimhoxha.plugins.http.GithubConnector;
import com.kujtimhoxha.plugins.matcher.*;
import com.kujtimhoxha.plugins.model.Todo;
import com.kujtimhoxha.plugins.model.github.GithubIssuePost;
import com.kujtimhoxha.plugins.model.github.GithubIssueResponse;
import com.kujtimhoxha.plugins.reader.JavaFileReader;
import org.apache.maven.plugin.MojoExecutionException;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * GithubClient.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class GithubClient implements Service{
    /**
     * File types to search for todo's.
     */
    private String[] types;

    /**
     * Files to search for todo's
     */
    private List<File> files;
    /**
     * Todo.json Path.
     */
    private String config;

    public GithubClient(List<File> files, String[] types, String config) {
        this.files=files;
        this.types = types;
        this.config = config;
    }

    @Override
    public void run() throws MojoExecutionException, IOException {
        List<Todo> todos=new ArrayList<Todo>();
        if(Arrays.asList(types).contains(".java")){
           todos.addAll(new JavaFileReader(files).todos());
        }
        removeClosed(todos);
        for (Todo todo:todos) {
            for (String comment:todo.getComments()) {
                if (!comment.contains("[issue=")) {
                    final GithubIssuePost issue = new GithubIssuePost();
                    final String title = new TitleFinder().find(comment);
                    if (title == null) throw new MojoExecutionException(
                            "Todo has no title in file " + todo.getFile().getPath()
                    );
                    final String body = new BodyFinder().find(comment);
                    if (body == null) throw new MojoExecutionException(
                            "Todo has no body in file " + todo.getFile().getPath()
                    );
                    final String assignee = new AssigneeFinder().find(comment);
                    final String milestone = new MilestoneFinder().find(comment);
                    final String labels = new LabelsFinder().find(comment);
                    issue.setTitle(title);
                    issue.setBody(body);
                    issue.setAssignee(assignee);
                    if(labels!=null) {
                        issue.setLabels(Arrays.asList(labels.split(",")));
                    }
                    issue.setMilestone(milestone);
                    try {
                        GithubIssueResponse response = new GithubConnector().createIssue(ConfigReader.getConfig(config), issue);
                        issueCreated(response, todo);
                    } catch (Exception e) {
                        throw new MojoExecutionException(e.getMessage());
                    }
                }
            }
        }

    }

    private void removeClosed(List<Todo> todos) throws IOException {
        List<GithubIssueResponse>issues=new GithubConnector().getIssues(ConfigReader.getConfig(config));
        for(GithubIssueResponse issue: issues){
            if(issue.getState().equals("closed")){
                for (Todo todo:todos){
                    for (String comment:todo.getComments()){
                        if (comment.contains(String.format("[issue=#%s]",issue.getNumber()))) {
                            BufferedReader file = new BufferedReader(new FileReader(todo.getFile()));
                            String line;String input = "";

                            while ((line = file.readLine()) != null) input += line + '\n';

                            file.close();
                            input = input.replace(
                                    new JavaTodoFinder().findForClosed(
                                            input,
                                            String.valueOf(issue.getNumber())
                                    ),
                                    ""
                            );
                            FileOutputStream fileOut = new FileOutputStream(todo.getFile());
                            fileOut.write(input.getBytes());
                            fileOut.close();
                        }
                    }
                }
            }
        }
    }

    public void issueCreated(GithubIssueResponse response, Todo todo) throws IOException {
        BufferedReader file = new BufferedReader(new FileReader(todo.getFile()));
        String line;String input = "";

        while ((line = file.readLine()) != null) input += line + '\n';

        file.close();
        input = input.replace(
                response.getTitle(),
                String.format(
                        "%s [issue=#%s]",
                        response.getTitle(),response.getNumber()
                )
        );
        FileOutputStream fileOut = new FileOutputStream(todo.getFile());
        fileOut.write(input.getBytes());
        fileOut.close();
    }
}
