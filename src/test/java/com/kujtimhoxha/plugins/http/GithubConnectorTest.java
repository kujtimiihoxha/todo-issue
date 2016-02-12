package com.kujtimhoxha.plugins.http;

import com.kujtimhoxha.plugins.config.ConfigReader;
import com.kujtimhoxha.plugins.config.Configurations;
import com.kujtimhoxha.plugins.exception.GithubException;
import com.kujtimhoxha.plugins.model.github.GithubIssueResponse;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * GithubConnectorTest.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class GithubConnectorTest {

    /**
     * Gets the issues from the repository
     *  according to the configuration file.
     * @throws Exception
     */
    @Test
    public void testGetIssues() throws Exception {
        Configurations config= ConfigReader.getConfig(
                System.getProperty("user.dir")+"/src/test/resources/todo-issue-github.json");
        List<GithubIssueResponse> response=new GithubConnector().getIssues(config);
        Assert.assertTrue(response.size()>0);
    }

    /**
     * Fails to get issues if repository or user
     *  are not right.
     * @throws Exception if something goes wrong.
     */
    @Test(expected = GithubException.class)
    public void testGetIssuesFail() throws Exception {
        Configurations config= ConfigReader.getConfig(
                System.getProperty("user.dir")+"/src/test/resources/todo-issue-github-incorrect-repository-user.json");

        try {
            List<GithubIssueResponse> response=new GithubConnector().getIssues(config);
        }
        catch (GithubException exp){
            Assert.assertEquals(
                    "Message from exception is not right",
                    "Repository or username not found",
                    exp.getMessage());
            throw exp;
        }

    }
    /**
     * Fails to get issues if github is unavailable
     *  or there is no internet connection.
     *
     * @throws Exception if something goes wrong.
     */
    @Test(expected = IOException.class)
    @Ignore
    public void testGetIssuesFailIOException() throws Exception {
        Configurations config= ConfigReader.getConfig(
                System.getProperty("user.dir")+"/src/test/resources/todo-issue-github-incorrect-repository-user.json");

        try {
            List<GithubIssueResponse> response=new GithubConnector().getIssues(config);
        }
        catch (Exception exp){
            Assert.assertEquals(
                    "Message from exception is not right",
                    "Could not reach : api.github.com",
                    exp.getMessage());
            throw exp;
        }

    }

    @Test
    public void testCreateIssue() throws Exception {

    }
}