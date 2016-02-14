package com.kujtimhoxha.plugins.todo;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * GradleRunnerTest.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class GradleRunnerTest {
    /**
     * Test if gradle runner works.
     * @throws Exception if something
     *  goes wrong.
     */
    @Test
    public void testRun() throws Exception {
        List<File> files = new ArrayList<File>();
        files.add(new File(System.getProperty("user.dir")+"/src/test/resources/project-valid/src/"));
        final GradleRunner runner =new GradleRunner(files);
        runner.run();
    }
}