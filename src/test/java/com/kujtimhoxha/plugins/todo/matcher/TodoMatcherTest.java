package com.kujtimhoxha.plugins.todo.matcher;

import com.kujtimhoxha.plugins.todo.model.Issue;
import org.junit.Assert;
import org.junit.Test;
import org.yaml.snakeyaml.scanner.ScannerException;
import java.io.*;
import java.util.List;

/**
 * TodoMatcherTest.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class TodoMatcherTest {
    /**
     * Tests if matcher finds 2 todos in this file.
     *
     * @throws Exception if something
     *  goes wrong.
     */
    @Test
    public void testMatch() throws Exception {
        List<Issue> issues = new TodoMatcher().match(
               new File(System.getProperty("user.dir")+"/src/test/resources/matcher/TestMultiple.todo")
        );
        Assert.assertTrue(issues.size() == 2);
    }

    /**
     * Will throw exception if yaml format is not write.
     *
     * @throws Exception if something
     *  goes wrong.
     */
    @Test(expected = ScannerException.class)
    public void testMatchYamlFormatFail() throws Exception {
        List<Issue> issues = new TodoMatcher().match(
            new File(
                System.getProperty("user.dir") +
                        "/src/test/resources/bad-todo-yaml-format.todo"
            )
        );

    }

    /**
     * Tests if matcher finds todos only in comments.
     *
     * @throws Exception if something
     *  goes wrong.
     */
    @Test
    public void testMatchCommentsOnly() throws Exception {
        List<Issue> issues = new TodoMatcher().match(
                new File(System.getProperty("user.dir")+"/src/test/resources/matcher/TestTodoPattern.java")
        );
        Assert.assertTrue(issues.size() == 0);
    }
}
