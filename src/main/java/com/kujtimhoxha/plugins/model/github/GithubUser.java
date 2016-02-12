package com.kujtimhoxha.plugins.model.github;

import com.google.api.client.util.Key;

/**
 * GithubUser.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class GithubUser {
    @Key("login")
    private String login;
    @Key("id")
    private Integer id;
    @Key("avatar_url")
    private String avatarUrl;
    @Key("gravatar_id")
    private String gravatarId;
    @Key("url")
    private String url;
    @Key("html_url")
    private String htmlUrl;
    @Key("followers_url")
    private String followersUrl;
    @Key("following_url")
    private String followingUrl;
    @Key("gists_url")
    private String gistsUrl;
    @Key("starred_url")
    private String starredUrl;
    @Key("subscriptions_url")
    private String subscriptionsUrl;
    @Key("organizations_url")
    private String organizationsUrl;
    @Key("repos_url")
    private String reposUrl;
    @Key("events_url")
    private String eventsUrl;
    @Key("received_events_url")
    private String receivedEventsUrl;
    @Key("type")
    private String type;
    @Key("site_admin")
    private Boolean siteAdmin;

    public String getLogin() {
        return login;
    }

    public Integer getId() {
        return id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getGravatarId() {
        return gravatarId;
    }

    public String getUrl() {
        return url;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getFollowersUrl() {
        return followersUrl;
    }

    public String getFollowingUrl() {
        return followingUrl;
    }

    public String getGistsUrl() {
        return gistsUrl;
    }

    public String getStarredUrl() {
        return starredUrl;
    }

    public String getSubscriptionsUrl() {
        return subscriptionsUrl;
    }

    public String getOrganizationsUrl() {
        return organizationsUrl;
    }

    public String getReposUrl() {
        return reposUrl;
    }

    public String getEventsUrl() {
        return eventsUrl;
    }

    public String getReceivedEventsUrl() {
        return receivedEventsUrl;
    }

    public String getType() {
        return type;
    }

    public Boolean isSiteAdmin() {
        return siteAdmin;
    }

    @Override
    public String toString() {
        return "GithubUser{" +
                "login='" + login + '\'' +
                ", id=" + id +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", gravatarId='" + gravatarId + '\'' +
                ", url='" + url + '\'' +
                ", htmlUrl='" + htmlUrl + '\'' +
                ", followersUrl='" + followersUrl + '\'' +
                ", followingUrl='" + followingUrl + '\'' +
                ", gistsUrl='" + gistsUrl + '\'' +
                ", starredUrl='" + starredUrl + '\'' +
                ", subscriptionsUrl='" + subscriptionsUrl + '\'' +
                ", organizationsUrl='" + organizationsUrl + '\'' +
                ", reposUrl='" + reposUrl + '\'' +
                ", eventsUrl='" + eventsUrl + '\'' +
                ", receivedEventsUrl='" + receivedEventsUrl + '\'' +
                ", type='" + type + '\'' +
                ", siteAdmin=" + siteAdmin +
                '}';
    }
}
