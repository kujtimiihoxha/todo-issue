package com.kujtimhoxha.plugins.http;

import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.ObjectParser;
import com.kujtimhoxha.plugins.config.ConfigReader;
import com.kujtimhoxha.plugins.model.github.GithubIssuePost;
import com.kujtimhoxha.plugins.model.github.GithubIssueResponse;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.*;
import java.util.List;


/**
 * GithubConnectorTest.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class GithubConnectorTest {

    @Test
    @Ignore
    public void testGetIssues() throws Exception {
        List<GithubIssueResponse> issues=new GithubConnector().getIssues(
            ConfigReader.getConfig(System.getProperty("user.dir")+"/todo-issue.json")
        );
        Assert.assertTrue("Issue get failed",issues.size()>0);
    }
    @Test
    @Ignore
    public void testCreateIssue() throws IOException {
        ObjectParser objectParser=new JsonObjectParser(new JacksonFactory());
        GithubIssuePost post=objectParser.parseAndClose(
                new BufferedReader(
                        new FileReader(
                            this.getClass().getClassLoader().getResource("issue.json").getFile()
                        )
                ),
                GithubIssuePost.class
        );
        GithubIssueResponse issue=new GithubConnector().createIssue(
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
}