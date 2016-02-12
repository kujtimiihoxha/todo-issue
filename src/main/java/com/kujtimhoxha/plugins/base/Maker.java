package com.kujtimhoxha.plugins.base;

import com.kujtimhoxha.plugins.config.Configurations;
import com.kujtimhoxha.plugins.model.Issue;

import java.io.File;
import java.io.IOException;

/**
 * Maker.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 * @param <Output> output object type.
 */
public interface Maker<Output> {
    /**
     * Make Output form Issue.
     * @param issue input object.
     * @param file file object.
     * @param conf configuration file.
     * @return output object.
     */
    Output make(Issue issue, File file, Configurations conf) throws Exception;
}
