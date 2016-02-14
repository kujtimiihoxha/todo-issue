package com.kujtimhoxha.plugins.todo.base;

import com.kujtimhoxha.plugins.todo.model.Issue;

import java.io.File;
import java.util.List;

/**
 * Matcher.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public interface Matcher {
    /**
     * Finds todos  in the file.
     * @param file file to search in.
     * @return list of issues.
     * @throws Exception if something
     *  goes wrong.
     */
    List<Issue> match(File file) throws Exception;
}
