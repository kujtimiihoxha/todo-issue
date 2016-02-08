Maven plugin to convert TODO-s to Github/Gitlab issues. [![Build Status](https://travis-ci.org/kujtimiihoxha/todo-issue.svg?branch=master)](https://travis-ci.org/kujtimiihoxha/todo-issue)
------------------------------------------------------
### About
Todo-issue is a plugin that lets you easily write github issues while you are coding.<br>This plugin enables the user to add comments in the source code that will be translated to github issues.<br>This will help the developing team to keep track of the bugs and tasks assigned to specific users.

### Syntax
 
     /**
      * [todo] $ Issue Title $
      * #/
      * Issue Body description
      * #/
      * ~kujtimiihoxha~
      * %bug,enhancement,any,label%
      * /`1.0.1/`
      */
 
 - ```[todo] ```: every todo that will be an issue must start with this tag. **required**
 - ```${title}$```: will be the title of the issue. **required**
 - ```#/{body}#/``` : will be the body of the issue.
 - ```~{assignee}~``` : issue assigned to.
 - ```%{label,label1,label2}%``` : labels of the issue separated by commas. 
 - ```/`{milestone}/``` : milestone 

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

 - ```git-server``` : can be "Github" or "Gitlab".
 - ```type``` : if using Gitlab can be "org" or "user".
 - ```repository-username``` : the repository user.
 - ```issuer-username``` : the user who is writing the issues.
 - ```token``` : the user access token.
 - ```repository``` : the repository to post issues to .
 - ```project-id``` : if the git-server is Gitlab you must provide the project-id
 
### Contribute
To help improve todo-issue clone this repo and submit pull requests with suggested changes.
Add unit tests for new features added and/or missing tests.
Please test your changes before sending a pull request by running :
``` mvn clean install ```
### Licence
The todo-issue is open-sourced software licensed under the [MIT license](http://opensource.org/licenses/MIT)