package com.kujtimhoxha.plugins.filter;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * ExcludedFilter.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class ExcludedFilter implements FilenameFilter {
    /**
     * Excluded files list.
     */
    private final List<File> files = new ArrayList<File>();

    /**
     * Ctr.
     * @param excludes excluded files
     */
    public ExcludedFilter(final List<File> excludes) {
        this.files.addAll(excludes);
    }

    @Override
    public final boolean accept(final File dir, final String name) {
        if (dir.exists()) {
            for (File exc : this.files) {
                if (dir.getAbsolutePath().equals(exc.getAbsolutePath())) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
