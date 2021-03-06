package com.kujtimhoxha.plugins.todo.modifier;

import com.kujtimhoxha.plugins.todo.base.Modifier;
import com.kujtimhoxha.plugins.todo.config.Configurations;
import com.kujtimhoxha.plugins.todo.exception.GithubException;
import com.kujtimhoxha.plugins.todo.http.GithubConnector;
import com.kujtimhoxha.plugins.todo.logger.Logger;
import com.kujtimhoxha.plugins.todo.model.Todo;
import com.kujtimhoxha.plugins.todo.model.github.GithubIssueResponse;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

/**
 * GithubTodoModifier.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class GithubTodoModifier implements Modifier<GithubIssueResponse> {

    @Override
    public final void issueCreated(final GithubIssueResponse response,
                               final Todo todo) throws IOException {
        final BufferedReader file =
                new BufferedReader(new FileReader(todo.getFile()));
        String line;
        String input = "";
        while ((line = file.readLine()) != null) {
            input += line + '\n';
        }
        file.close();
        final String[] lines = input.split("\\n");
        for (String current : lines) {
            if (current.replaceAll(" ", "")
                    .equals(
                            "title:" + response.getTitle()
                                    .replaceAll(" ", "")
                    )) {
                input = input.replace(
                        current + "\n",
                        current + "\n" + current
                                .replace("title: ", "id: ")
                                .replace(
                                        response.getTitle(),
                                        String.valueOf(
                                                response.getNumber()
                                        )
                                ) + "\n"
                );
            }
        }
        FileOutputStream fileOut =
                new FileOutputStream(todo.getFile());
        fileOut.write(input.getBytes());
        fileOut.close();
    }


    @Override
    public final void removeClosed(final List<Todo> todos,
                              final Configurations conf)
            throws IOException, GithubException {
        List<GithubIssueResponse> issues =
                new GithubConnector().getIssues(conf);
        for (GithubIssueResponse issue : issues) {
            if (issue.getState().equals("closed")) {
                for (Todo todo : todos) {
                    if (todo.getIssue().getId() != null) {
                        if (todo.getIssue().getId().intValue()
                                == issue.getNumber().intValue()) {
                            Logger.getlog().info(
                                    "Todo with issue id #"
                                            + issue.getNumber()
                                            + " is closed and will be deleted"
                            );
                        }
                        final BufferedReader file =
                                new BufferedReader(
                                        new FileReader(
                                                todo.getFile()
                                        )
                                );
                        String line;
                        final StringBuilder input =
                                new StringBuilder("");
                        while ((line = file.readLine()) != null) {
                            input.append(line).append('\n');
                        }
                        file.close();
                        final Pattern patternComment = Pattern.compile(
                                "(/\\*([^*]|[\\r\\n]|"
                                        + "(\\*+([^*/]|"
                                        + "[\\r\\n])))*\\*+/)|"
                                        + "(<!--([\\s\\S]*?)-->)|"
                                        + "(=begin([\\s\\S]*?)=end)|"
                                        + "(=pod([\\s\\S]*?)=cut)"
                        );
                        final java.util.regex.Matcher matcher =
                                patternComment.matcher(
                                        input.toString()
                                );
                        while (matcher.find()) {
                            if (matcher.group(0).replaceAll(" ", "")
                                    .contains("id:" + issue.getNumber())) {

                                final String output =
                                        input.toString()
                                                .replace(
                                                        matcher.group(0), ""
                                                );
                                FileOutputStream fileOut =
                                        new FileOutputStream(todo.getFile());
                                fileOut.write(output.getBytes());
                                fileOut.close();
                                break;
                            }
                        }

                    }
                }
            }
        }
    }
}
