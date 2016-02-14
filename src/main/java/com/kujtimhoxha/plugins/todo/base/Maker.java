package com.kujtimhoxha.plugins.todo.base;

import com.kujtimhoxha.plugins.todo.config.Configurations;
import com.kujtimhoxha.plugins.todo.model.Issue;

import java.io.File;

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
     * @throws Exception if something
     *  goes wrong.
     * @return output object.
     */
    Output make(Issue issue, File file, Configurations conf) throws Exception;
}
