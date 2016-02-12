package com.kujtimhoxha.plugins.matcher;

import com.kujtimhoxha.plugins.model.Issue;
import org.junit.Assert;
import org.junit.Test;
import org.yaml.snakeyaml.constructor.ConstructorException;
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
            new File(
                System.getProperty("user.dir") +
                        "/src/test/resources/languages/test.html"
            )
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
}
