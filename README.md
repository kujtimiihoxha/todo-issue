Maven plugin to convert TODO-s to Github/Gitlab issues. [![Build Status](https://travis-ci.org/kujtimiihoxha/todo-issue.svg?branch=master)](https://travis-ci.org/kujtimiihoxha/todo-issue)
------------------------------------------------------
### About
Todo-issue is a plugin that lets you easily write github issues while you are coding,this plugin enables the user to 
add comments in the source code that will be translated to github issues, this will help the developing team to keep
track of the bugs and tasks assigned to specific users.



### todo.json
```json
{
  "git-server":"Github",
  "type":"user",
  "repository-username":"kujtimiihoxha",
  "issuer-username":"kujtimiihoxha",
  "token":"token",
  "repository":"todo-issue",
  "project-id":"0"
}

```

 - git-server: can be "Github" or "Gitlab".
 - type: if using Gitlab can be "org" or "user".
 - repository-username: the repository user.
 - issuer-username: the user who is writing the issues.
 - token: the user access token.
 - repository: the repository to post issues to .
 - project-id: if the git-server is Gitlab you must provide the project-id