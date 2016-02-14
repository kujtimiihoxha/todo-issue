package com.kujtimhoxha.plugins.todo.logger;

import org.apache.maven.plugin.logging.SystemStreamLog;

/**
 * Logger.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class Logger extends SystemStreamLog {
    /**
     * Todo-issue Logger.
     */
    private static Logger log = new Logger();

    /**
     * Gets the logger.
     * @return the logger object
     */
    public static Logger getlog() {
        return log;
    }

    /**
     * Private ctr.
     */
    private Logger() {
    }
}
