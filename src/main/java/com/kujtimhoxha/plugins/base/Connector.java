package com.kujtimhoxha.plugins.base;

import com.kujtimhoxha.plugins.config.Configurations;
import java.io.IOException;
import java.util.List;

/**
 * Connector.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 * @param <CreateIssueRequest> Create issue request model.
 * @param <IssueResponse> Issue response model.
 */
public interface Connector<IssueResponse, CreateIssueRequest> {
    /**
     * Get issues from project.
      * @param config configurations.
     * @return response with issues.
     * @throws Exception if something goes wrong
     *  when connecting to the server.
     */
    List<IssueResponse> getIssues(Configurations config) throws Exception;

    /**
     * Creates an issue to the project.
     * @param config configurations
     * @param issue response with issue created.
     * @return created issue reponse
     * @throws IOException if something goes wrong
     *  when connecting to the server.
     */
    IssueResponse createIssue(Configurations config, CreateIssueRequest issue)
            throws Exception;
}
