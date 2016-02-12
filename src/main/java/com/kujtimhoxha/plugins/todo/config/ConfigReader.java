package com.kujtimhoxha.plugins.todo.config;

import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.ObjectParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * ConfigReader.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class ConfigReader {

    /**
     * Get the configuration object.
     * @param path path to the configuration json.
     * @return the configuration object.
     * @throws IOException if there is a problem
     *  reading the configuration jason.
     */
    public static Configurations getConfig(final String path)
            throws IOException {
        ObjectParser parser = new JsonObjectParser(new JacksonFactory());
        BufferedReader br = new BufferedReader(new FileReader(path));
        return parser.parseAndClose(br, Configurations.class);
    }

    /**
     * Private ctr.
     */
    private ConfigReader() {

    }
}
