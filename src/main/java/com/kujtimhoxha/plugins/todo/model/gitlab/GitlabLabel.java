package com.kujtimhoxha.plugins.todo.model.gitlab;

import com.google.api.client.util.Key;

/**
 * GitlabLabel.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class GitlabLabel {
    @Key("name")
    private String name;
    @Key("color")
    private String color;

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "GithubLabel{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
