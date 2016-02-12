package com.kujtimhoxha.plugins.base;

import com.kujtimhoxha.plugins.config.Configurations;

import java.io.File;
import java.util.List;

/**
 * Finder.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public interface Finder {
    /**
     * Find.
     * @param files files to search.
     * @param conf configuration file.
     * @throws Exception if something goes
     *  wrong.
     */
    void find(List<File> files, Configurations conf) throws Exception;
}
