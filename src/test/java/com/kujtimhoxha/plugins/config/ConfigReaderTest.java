package com.kujtimhoxha.plugins.config;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;


/**
 * ConfigReaderTest.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class ConfigReaderTest {

    /**
     * Reads the configuration file.
     * @throws Exception if something
     *  goes wrong.
     */
    @Test
    public void testGetConfig() throws Exception {
        Configurations config=ConfigReader.getConfig(
                System.getProperty("user.dir")+"/src/test/resources/todo-issue-github.json");
        Assert.assertEquals(
                "Content read is not what is expected",
                "Github",
                config.getGitServer()
        );
    }

    /**
     * Throws io exception if the file
     *  is not in the location.
     * @throws Exception if something
     *  goes wrong.
     */
    @Test(expected = IOException.class)
    public void testGetConfigFail() throws Exception {
        Configurations config=ConfigReader.getConfig(
                System.getProperty("user.dir")+"/src/test/todo.json");
    }
}