package com.kujtimhoxha.plugins.modifiers;

import com.kujtimhoxha.plugins.base.Modifier;
import com.kujtimhoxha.plugins.config.Configurations;
import com.kujtimhoxha.plugins.exception.GithubException;
import com.kujtimhoxha.plugins.exception.GitlabException;
import com.kujtimhoxha.plugins.http.GitlabConnector;
import com.kujtimhoxha.plugins.logger.Logger;
import com.kujtimhoxha.plugins.model.Todo;
import com.kujtimhoxha.plugins.model.gitlab.GitLabIssueResponse;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

/**
 * GitlabTodoModifier.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class GitlabTodoModifier implements Modifier<GitLabIssueResponse>{
     @Override
     public void issueCreated(final GitLabIssueResponse response,
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
                        current+"\n",
                        current + "\n" + current
                                .replace("title", "id")
                                .replace(
                                        response.getTitle(),
                                        String.valueOf(
                                                response.getId()
                                        )
                                ) +"\n"
                );
            }
        }
        FileOutputStream fileOut =
                new FileOutputStream(todo.getFile());
        fileOut.write(input.getBytes());
        fileOut.close();
    }

    @Override
    public  void removeClosed(final List<Todo> todos,
                              final Configurations conf)
            throws IOException, GitlabException {
        List<GitLabIssueResponse> issues =
                new GitlabConnector().getIssues(conf);
        for (GitLabIssueResponse issue : issues) {
            if (issue.getState().equals("closed")) {
                for (Todo todo : todos) {
                    if (todo.getIssue().getId() != null) {
                        if (todo.getIssue().getId().intValue()
                                == issue.getId().intValue()) {
                            Logger.getlog().info(
                                    "Todo with issue id #"
                                            + issue.getId()
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
                                    .contains("id:" + issue.getId())) {

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
