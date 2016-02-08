package com.kujtimhoxha.maven.config

import org.junit.Assert
import org.junit.Test

/**
 * ConfigReaderTest.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
class ConfigReaderTest {

    /**
     * Read the configuration json
     */
    @Test
    void testGetConfig() {
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
    void testErrorIfPathNotRight(){
        ConfigReader.getConfig(
                System.getProperty("user.dir")+"/falsePath.json"
        ).getGitServer()
    }
}
