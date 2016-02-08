Maven plugin to convert TODO-s to Github/Gitlab issues.
------------------------------------------------------
Description.

### todo.json
```json
{
  "git-server":"Github",
  "type":"org",
  "username":"kujtimiihoxha",
  "token":"token",
  "repository":"todo-issue"
}
```

 - git-server: can be "Github" or "Gitlab".
 - type: can be "org" or "user".
 - username: the user that will be the issue writer.
 - token: the user access token.
 - repository: the repository to post issues to .