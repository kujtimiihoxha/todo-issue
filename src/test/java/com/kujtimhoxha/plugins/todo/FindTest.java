package com.kujtimhoxha.plugins.todo;

import com.kujtimhoxha.plugins.todo.Find;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.testing.MojoRule;
import org.junit.Rule;
import org.junit.Test;

import java.io.File;

/**
 * FindTest.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class FindTest {
    /**
     * MojoRule.
     */
    @Rule
    public MojoRule rule = new MojoRule();

    /**
     * Test if the plugin throws exception when
     *  the configuration file is not right.
     * @throws Exception if something goes wrong.
     */
    @Test(expected = MojoExecutionException.class)
    public final void wrongConfigurationFile() throws Exception {
        File testProject = new File(
            System.getProperty("user.dir"),
            "src/test/resources/project-invalid/"
        );
        Find mojo = (Find) this.rule.lookupConfiguredMojo(testProject, "find");
        mojo.execute();
    }

    /**
     * Everything is ok with the settings.
     * @throws Exception if something goes wrong.
     */
    @Test

    public final void everythingOk() throws Exception {
        File testProject = new File(
            System.getProperty("user.dir"),
            "src/test/resources/project-valid/"
        );
        Find mojo = (Find) this.rule.lookupConfiguredMojo(testProject, "find");
        mojo.execute();
    }
}
