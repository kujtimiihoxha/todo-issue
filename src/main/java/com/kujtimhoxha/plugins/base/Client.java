package com.kujtimhoxha.plugins.base;

import com.kujtimhoxha.plugins.config.Configurations;
import com.kujtimhoxha.plugins.model.Todo;
import java.util.List;

/**
 * Client.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public interface Client {
    /**
     * Run the client.
     *
     * @param todos todos to  be sent.
     * @param  conf configuration file.
     * @throws Exception if something goes wrong
     *  in the connector.
     */
    void run(List<Todo> todos, Configurations conf) throws Exception;
}
