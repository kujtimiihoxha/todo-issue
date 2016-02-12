package com.kujtimhoxha.plugins.http;

import com.kujtimhoxha.plugins.config.ConfigReader;
import com.kujtimhoxha.plugins.config.Configurations;
import com.kujtimhoxha.plugins.exception.GitlabException;
import com.kujtimhoxha.plugins.model.gitlab.GitLabIssueResponse;
import com.kujtimhoxha.plugins.model.gitlab.GitlabErrorResponse;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * GitlabConnectorTest.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class GitlabConnectorTest {
    /**
    * Fails to get issues if repository or user
    *  are not right.
    * @throws Exception if something goes wrong.
    */
    @Test(expected = GitlabException.class)
    public void testGetIssuesFail() throws Exception {
        Configurations config= ConfigReader.getConfig(
                System.getProperty("user.dir")+"/src/test/resources/todo-issue-gitlab-incorrect-repository-user.json");

//        try {
            List<GitLabIssueResponse> response=new GitlabConnector().getIssues(config);
//        }
//        catch (GitlabErrorResponse exp){
//            Assert.assertEquals(
//                    "Message from exception is not right",
//                    "Repository or username not found",
//                    exp.getMessage());
//            throw exp;
//        }

    }
}