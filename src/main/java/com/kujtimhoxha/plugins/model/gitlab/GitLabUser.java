package com.kujtimhoxha.plugins.model.gitlab;

import com.google.api.client.util.Key;

/**
 * GitLabUser.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class GitLabUser {
    @Key("state")
    private String state;
    @Key("web_url")
    private String webUrl;
    @Key("avatar_url")
    private String avatarUrl;
    @Key("username")
    private String username;
    @Key("id")
    private Integer id;
    @Key("name")
    private String name;

    public String getState() {
        return state;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getUsername() {
        return username;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
