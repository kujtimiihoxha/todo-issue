package com.kujtimhoxha.plugins.base;

import com.kujtimhoxha.plugins.model.Todo;
import java.io.IOException;
import java.util.List;

/**
 * Reader.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public interface Reader {
    /**
     * Returns the todo-s from a file.
     * @return todo-s
     * @throws IOException if something
     *  goes wrong
     */
    List<Todo> todos() throws IOException;
}
