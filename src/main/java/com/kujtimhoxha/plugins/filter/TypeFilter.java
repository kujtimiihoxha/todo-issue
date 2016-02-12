package com.kujtimhoxha.plugins.filter;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

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
    private  final List<String> extensions = new ArrayList<String>();

    /**
     * Ctr.
     * @param includes TypeFilter to accept.
     */
    public TypeFilter(final List<String> includes) {
        this.extensions.addAll(includes);
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
