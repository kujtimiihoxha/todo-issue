package com.kujtimhoxha.plugins.todo.http;

import com.kujtimhoxha.plugins.todo.config.ConfigReader;
import com.kujtimhoxha.plugins.todo.config.Configurations;
import com.kujtimhoxha.plugins.todo.exception.GitlabException;
import com.kujtimhoxha.plugins.todo.model.gitlab.GitLabIssueResponse;
import org.junit.Test;

import java.util.List;

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