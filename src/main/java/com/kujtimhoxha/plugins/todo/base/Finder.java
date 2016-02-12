package com.kujtimhoxha.plugins.todo.base;

/**
 * Finder.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 * @param <Return> value returned from finder.
 */
public interface Finder<Return> {
    /**
     * Find.
     * @throws Exception if something goes
     *  wrong.
     *  @return the found objects.
     */
    Return find() throws Exception;
}
