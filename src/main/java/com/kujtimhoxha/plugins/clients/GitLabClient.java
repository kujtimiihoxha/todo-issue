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
package com.kujtimhoxha.plugins.clients;

import com.kujtimhoxha.plugins.base.Service;
import com.kujtimhoxha.plugins.config.ConfigReader;
import com.kujtimhoxha.plugins.config.Configurations;
import com.kujtimhoxha.plugins.http.GithubConnector;
import com.kujtimhoxha.plugins.http.GitlabConnector;
import com.kujtimhoxha.plugins.logger.Log;
import com.kujtimhoxha.plugins.matcher.*;
import com.kujtimhoxha.plugins.model.Todo;
import com.kujtimhoxha.plugins.model.github.GithubIssuePost;
import com.kujtimhoxha.plugins.model.github.GithubIssueResponse;
import com.kujtimhoxha.plugins.model.gitlab.GitLabIssueResponse;
import com.kujtimhoxha.plugins.model.gitlab.GitlabIssuePost;
import com.kujtimhoxha.plugins.reader.JavaFileReader;
import org.apache.maven.plugin.MojoExecutionException;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * GitLabClient.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class GitLabClient implements Service{
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

    public GitLabClient(List<File> files, String[] types, String config) {
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
        if(todos.size()==0)Log.getLog().info("No TODO-s were found");
        removeClosed(todos);
        for (Todo todo:todos) {
            Log.getLog().info("In file "+todo.getFile().getName()+" found "+todo.getComments().size()+" TODO-s");
            for (String comment:todo.getComments()) {
                if (!comment.contains("[issue=")) {
                    Log.getLog().info("New TODO found in file : "+todo.getFile().getName());
                    final GitlabIssuePost issue = new GitlabIssuePost();
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
                    final StringBuilder builder=new StringBuilder(body);
                    final Configurations configurations= ConfigReader.getConfig(config);
                    builder.append("\n").append("\n").append("\n").append(
                            String.format("```todo-issue``` File : [%s](%s)\n",
                            todo.getFile().getName(),
                                    configurations.getGitlabUrl()+
                                            "/"+
                                            configurations.getRepositoryUsername()+
                                    "/"+configurations.getRepository()+
                                    "/tree/master"+todo.getFile().getPath().replace(
                                    System.getProperty("user.dir"),""))
                    );
                    issue.setDescription(builder.toString());
                    if(assignee!=null)issue.setAssigneeId(new GitlabConnector().getUser(configurations,assignee).getId());
                    issue.setLabels(labels);
                    if(milestone!=null)issue.setMilestoneId(new GitlabConnector().getMilestone(configurations,milestone).getId());
                    Log.getLog().info("Issue  with title `"+title+"` will be created");
                    try {
                        GitLabIssueResponse response = new GitlabConnector().createIssue(configurations, issue);
                        issueCreated(response, todo);
                        Log.getLog().info("Issue created with id #"+response.getId());
                    } catch (Exception e) {
                        throw new MojoExecutionException(e.getMessage());
                    }
                }
            }
        }

    }

    private void removeClosed(List<Todo> todos) throws IOException {
        List<GitLabIssueResponse>issues=new GitlabConnector().getIssues(ConfigReader.getConfig(config));
        for(GitLabIssueResponse issue: issues){
            if(issue.getState().equals("closed")){
                for (Todo todo:todos){
                    for (String comment:todo.getComments()){
                        if (comment.contains(String.format("[issue=#%s]",issue.getId()))) {
                            Log.getLog().info("TODO with issue id #"+issue.getId()+" is closed and will be deleted");
                            BufferedReader file = new BufferedReader(new FileReader(todo.getFile()));
                            String line;String input = "";

                            while ((line = file.readLine()) != null) input += line + '\n';

                            file.close();
                            input = input.replace(
                                    new JavaTodoFinder().findForClosed(
                                            input,
                                            String.valueOf(issue.getId())
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

    public void issueCreated(GitLabIssueResponse response, Todo todo) throws IOException {
        BufferedReader file = new BufferedReader(new FileReader(todo.getFile()));
        String line;String input = "";

        while ((line = file.readLine()) != null) input += line + '\n';

        file.close();
        input = input.replace(
                response.getTitle(),
                String.format(
                        "%s [issue=#%s]",
                        response.getTitle(),response.getId()
                )
        );
        FileOutputStream fileOut = new FileOutputStream(todo.getFile());
        fileOut.write(input.getBytes());
        fileOut.close();
    }
}
