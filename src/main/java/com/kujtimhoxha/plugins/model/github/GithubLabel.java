package com.kujtimhoxha.plugins.model.github;

import com.google.api.client.util.Key;

/**
 * GithubLabel.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class GithubLabel {
    @Key("url")
    private String url;
    @Key("name")
    private String name;
    @Key("color")
    private String color;

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "GithubLabel{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
