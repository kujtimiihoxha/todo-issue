package com.kujtimhoxha.plugins.http;

import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.ObjectParser;
import com.kujtimhoxha.plugins.config.ConfigReader;
import com.kujtimhoxha.plugins.model.github.GithubIssuePost;
import com.kujtimhoxha.plugins.model.github.GithubIssueResponse;
import com.kujtimhoxha.plugins.model.gitlab.GitLabIssueResponse;
import com.kujtimhoxha.plugins.model.gitlab.GitLabUser;
import com.kujtimhoxha.plugins.model.gitlab.GitlabIssuePost;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * GitlabConnectorTest.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class GitlabConnectorTest {

    @Test
    @Ignore
    public void testGetIssues() throws Exception {
        List<GitLabIssueResponse> issues=new GitlabConnector().getIssues(
                ConfigReader.getConfig(System.getProperty("user.dir")+"/todo-issue.json")
        );
        Assert.assertTrue("Issue get failed",issues.size()>0);
    }

    @Test
    @Ignore
    public void testCreateIssue() throws IOException {
        ObjectParser objectParser=new JsonObjectParser(new JacksonFactory());
        GitlabIssuePost post=new GitlabIssuePost();
        post.setTitle("My Test Issue 3");
        post.setDescription("My Test Body 3");
        post.setLabels("labe1,label2");
        GitLabIssueResponse issue=new GitlabConnector().createIssue(
                ConfigReader.getConfig(
                        System.getProperty("user.dir")+"/todo-issue.json"
                ),
                post
        );
        Assert.assertEquals(
                "Response title does not equal with issue posted",
                post.getTitle(),
                issue.getTitle()
        );
    }
    @Test
    @Ignore
    public void testGetUser() throws IOException {
        ObjectParser objectParser=new JsonObjectParser(new JacksonFactory());
        GitLabUser gitLabUser=new GitlabConnector().getUser(  ConfigReader.getConfig(System.getProperty("user.dir")+"/todo-issue.json"),"kujtimiihoxha");
        Assert.assertEquals(
                "Response title does not equal with issue posted",
                "kujtimiihoxha",
                gitLabUser.getUsername()
        );
    }}