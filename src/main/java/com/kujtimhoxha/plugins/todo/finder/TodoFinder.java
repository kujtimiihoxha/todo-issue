package com.kujtimhoxha.plugins.todo.finder;

import com.kujtimhoxha.plugins.todo.base.Finder;
import com.kujtimhoxha.plugins.todo.matcher.TodoMatcher;
import com.kujtimhoxha.plugins.todo.model.Issue;
import com.kujtimhoxha.plugins.todo.model.Todo;
import org.apache.maven.plugin.MojoExecutionException;
import org.yaml.snakeyaml.constructor.ConstructorException;
import org.yaml.snakeyaml.scanner.ScannerException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * TodoFinder.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class TodoFinder implements Finder {
    /**
     * List of files to search for todo tags.
     */
    private final List<File> files;

    /**
     * Ctr.
     * @param fls files
     */
    public TodoFinder(final List<File> fls) {
        this.files = fls;
    }

    @Override
    public final List<Todo> find()
            throws MojoExecutionException {
        final List<Todo> todos = new ArrayList<Todo>();
        for (File file : files) {
            try {
                for (final Issue issue : new TodoMatcher().match(file)) {
                    todos.add(new Todo(file, issue));
                }
            } catch (IOException exp) {
                final MojoExecutionException exception =
                    new MojoExecutionException(exp.getMessage());
                exception.setStackTrace(exception.getStackTrace());
                throw exception;
            } catch (ScannerException exp) {
                final MojoExecutionException exception =
                    new MojoExecutionException(
                        "Could not parse yaml : " + exp.getMessage()
                    );
                exception.setStackTrace(exception.getStackTrace());
                throw exception;
            } catch (ConstructorException exp) {
                final MojoExecutionException exception =
                    new MojoExecutionException(
                        "Could not construct object : " + exp.getMessage()
                    );
                exception.setStackTrace(exception.getStackTrace());
                throw exception;
            }
        }
        return todos;
    }
}
