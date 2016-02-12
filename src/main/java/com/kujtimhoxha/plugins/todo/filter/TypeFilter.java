package com.kujtimhoxha.plugins.todo.filter;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * TypeFilter.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class TypeFilter implements FilenameFilter {
    /**
     * List of extensions to accept.
     */
    private Set<String> extensions;

    /**
     * Ctr.
     * @param includes TypeFilter to accept.
     */
    public TypeFilter(final List<String> includes) {
        this.extensions = new HashSet<String>(includes);
    }

    @Override
    public final boolean accept(final File dir, final String name) {
        if (dir.exists()) {
            for (String ext: this.extensions) {
                if (name.contains(ext)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }
}
