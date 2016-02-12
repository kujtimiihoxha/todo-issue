package com.kujtimhoxha.plugins.todo.model;

import java.io.File;

/**
 * Todo.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Todo {
    
    private File file;
    
    private Issue issue;

    public Todo(File file, Issue issue) {
        this.file=file;
        this.issue=issue;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }
}
