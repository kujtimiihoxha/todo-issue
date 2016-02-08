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
     * Read the configuration json
     */
    @Test
    public void testGetConfig() throws IOException {
        Assert.assertEquals(
                "Config file read was successful",
                "Github",
                ConfigReader.getConfig(
                    System.getProperty("user.dir")+"/todo.json"
                ).getGitServer()
        );
    }
    /**
     * Throws exception if path not right
     */

    @Test(expected = IOException.class)
    public void testErrorIfPathNotRight() throws IOException {
        ConfigReader.getConfig(
                System.getProperty("user.dir")+"/falsePath.json"
        ).getGitServer();
    }
}
