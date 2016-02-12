package com.kujtimhoxha.plugins.todo.filter;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private final Set<File> files = new HashSet<File>();

    /**
     * Ctr.
     * @param excludes excluded files
     */
    public ExcludedFilter(final List<File> excludes) {
        this.files.addAll(new HashSet<File>(excludes));
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
