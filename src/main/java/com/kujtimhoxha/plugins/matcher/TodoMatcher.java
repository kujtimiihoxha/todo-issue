package com.kujtimhoxha.plugins.matcher;

import com.kujtimhoxha.plugins.base.Matcher;
import com.kujtimhoxha.plugins.model.Issue;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.ConstructorException;
import org.yaml.snakeyaml.scanner.ScannerException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * TodoMatcher.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class TodoMatcher implements Matcher {
    /**
     * Pattern for todos.
     */
    private static final Pattern PATTERN = Pattern.compile(
            "@todo([\\s\\S]*?)@end"
    );

    /**
     * File content.
     */
    private StringBuilder content = new StringBuilder("");

    @Override
    public final List<Issue> match(final File file)
            throws IOException, ScannerException {
        this.content.append(getContent(file));
        final java.util.regex.Matcher matcher = PATTERN.matcher(
                this.content.toString()
        );
        final List<Issue> issues = new ArrayList<Issue>();
        while (matcher.find()) {
            issues.add(build(matcher.group(1)));
        }
        return issues;
    }

    /**
     * Builds the issue object.
     * @param todo todo content.
     * @return issue object.
     * @throws ScannerException if
     *  something goes wrong with the yaml parsing.
     * @throws ConstructorException if the type of a
     *  variable given does not match the real type.
     */
    private Issue build(final String todo)
            throws ScannerException, ConstructorException {
        final Yaml yaml = new Yaml();
        final Issue issue = yaml.loadAs(todo, Issue.class);
        issue.setLineNumber(
                this.getLineNumber(
                        issue.getTitle()
                )
        );
        return issue;
    }

    /**
     * Get the content of the file.
     * @param file file to read content from.
     * @return the content of the file.
     * @throws IOException if something
     *  goes wrong.
     */
    private String getContent(final File file) throws IOException {
        final StringBuilder comment = new StringBuilder("");
        String line;
        final InputStream fis = new FileInputStream(file);
        final InputStreamReader isr = new InputStreamReader(
                fis,
                Charset.forName("UTF-8")
        );
        BufferedReader br = new BufferedReader(isr);

        while ((line = br.readLine()) != null) {
            comment.append(line).append("\n");
        }
        br.close();
        return comment.toString();
    }

    /**
     * Get the line number of the todo.
     * @param title the title of the issue.
     * @return lineNumber.
     */
    private int getLineNumber(final String title) {
        int lineNumber = 0;
        final String[] lines = this.content.toString().split("\\r?\\n");
        for (final String line : lines) {
            if (line.contains(title)) {
                return lineNumber;
            }
            lineNumber++;
        }
        return lineNumber;
    }
}
