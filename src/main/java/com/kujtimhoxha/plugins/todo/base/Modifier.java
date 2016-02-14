package com.kujtimhoxha.plugins.todo.base;

import com.kujtimhoxha.plugins.todo.config.Configurations;
import com.kujtimhoxha.plugins.todo.model.Todo;
import java.util.List;

/**
 * Modifier.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 *  @param <Response> the object received from the server.
 */
public interface Modifier<Response> {

    /**
     * After issue is created add the issue Id to the todo.
     * @param response response object.
     * @param todo todo object.
     * @throws Exception if something
     *  goes wrong.
     */
    void issueCreated(final Response response,
                      final Todo todo)
            throws Exception;

    /**
     * Removes closed issues from files.
     * @param todos todos object.
     * @param conf configuration object.
     * @throws Exception  if there is a
     *  problem with the github connection.
     */
    void removeClosed(final List<Todo> todos,
                      final Configurations conf)
            throws Exception;
}
