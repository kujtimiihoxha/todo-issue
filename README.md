Maven plugin to convert TODO-s to Github/Gitlab issues. [![Build Status](https://travis-ci.org/kujtimiihoxha/todo-issue.svg?branch=master)](https://travis-ci.org/kujtimiihoxha/todo-issue)
------------------------------------------------------
### About
Todo-issue is a plugin that lets you easily write github issues while you are coding.This plugin enables the user to add comments in the source code that will be translated to github issues.This will help the developing team to keep track of the bugs and tasks assigned to specific users.
#### See it in action
To se a demo project clone [this](https://github.com/kujtimiihoxha/testing-todo-issue) repository and follow the instructions.
### Syntax
 
     /**
      * [todo] $ Issue Title $
      * #/
      * Issue Body.
      * ----------------------
      *     ## My List
      *       - Item1
      *       - Item2
      * ### Some code.
      *  ```java
      *  private static final String hello="Hello World";
      *    ``` 
      * [Markdown](http://supported.com)
      * #/
      * ~kujtimiihoxha~
      * %bug,enhancement,any,label%
      * /`1.0.1/`
      */
 
 - ```[todo] ```: every todo that will be an issue must start with this tag. **required**
 - ```${title}$```: will be the title of the issue. **required**
 - ```#/{body}#/``` : will be the body of the issue. **required**
 - ```~{assignee}~``` : issue assigned to.
 - ```%{label,label1,label2}%``` : labels of the issue separated by commas. 
 - ```/`{milestone}/``` : milestone.

### todo.json
Before you can run the ```find``` goal you must add the file todo.json to the base directory(where the pom.xml is).
Remember to add todo.json to the .gitignore file because it contains your access token.
```json
{
  "git-server":"Github",
  "gitlab-url":"https://gitlab.com/",
  "type":"user",
  "repository-username":"kujtimiihoxha",
  "issuer-username":"kujtimiihoxha",
  "token":"token",
  "repository":"todo-issue",
  "project-id":"0"
}

```

 - ```git-server``` : can be "Github" or "Gitlab".
 - ```gitlab-url``` : if you are using "Gitlab" you must provide the gitlab server url.
 - ```type``` : if you are using Gitlab can be "org" or "user".
 - ```repository-username``` : the repository user.
 - ```issuer-username``` : the user who is writing the issues.
 - ```token``` : the user access token.
 - ```repository``` : the repository to post issues to .
 - ```project-id``` : if the git-server is Gitlab you must provide the project-id
 
### Usage
- First add a properly formatted todo.json file to your base directory.
- To execute ```find``` goal add the plugin to your build tag
```xml
    <build>
        <plugins>
            <plugin>
                <groupId>com.kujtimhoxha.plugins</groupId>
                <artifactId>todo-issue-plugin</artifactId>
                <version>0.3</version>
                <configuration>
                    <source>/src/main</source>
                </configuration>
                <executions>
                    <execution>
                        <goals>
                            <goal>find</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
```
- To execute find goal from command line add the plugin to the dependencies of your project and run:<br>
```bash 
    mvn clean com.kujtimhoxha.plugins:todo-issue-plugin:find -Dsource={source-root-path}
```
```xml
    <dependencies>
        <dependency>
            <groupId>com.kujtimhoxha.plugins</groupId>
            <artifactId>todo-issue-plugin</artifactId>
            <version>0.3</version>
        </dependency>
    </dependencies>
```
- -Dsource : sets the source root **required** 
- To exclude files/folders add ```-Dexcludes```:<br>
```bash 
mvn clean com.kujtimhoxha.plugins:todo-issue-plugin:find -Dsource={source-root-path} -Dexcludes={excluded-path-one},{excluded-path-two}
```
- To add a different ```todo.json``` add ``` -Dconfig={new-config-path}```.

### Using with gradle
To use todo-issue with gradle you need to add todo-issue to the dependencies.
- Add to buildscript
```
buildscript {
    repositories {
        mavenLocal()
    }
    dependencies {
        classpath "com.kujtimhoxha.plugins:todo-issue-plugin:0.3"
    }
}
```
- Add dependency to the project
```
dependencies {
    compile "com.kujtimhoxha.plugins:todo-issue-plugin:0.3"
}
```
- Add a task called find
```
task find(){
    def todo= new  com.kujtimhoxha.plugins.Find();
    todo.gradleAdapter("/src",null,null,null)
    todo.execute();
}
```

The ```gradleAdapter()``` method accepts four parameters 

- source : String - Source directory **required**
- excludes : String[]/null - Array of excluded directories or files (default : no file/folder is excluded )
- types : String[]/null - Array of file types to read from (default : ".java")
- config : String/null - Configuration file path (default : "{user.dir}/todo.json")
To run the task simply run 
```
gradle task:find
```
For a demo project with gradle see [this](https://github.com/kujtimiihoxha/gradle-todo-issue) repository.

### Contribute

To help improve todo-issue clone this repo and submit pull requests with suggested changes.<br>
Add unit tests for new features added and/or missing tests.<br>
Please test your changes before sending a pull request by running :<br>
```bash
 mvn clean install 
```   
### Licence

The todo-issue is open-sourced software licensed under the [MIT license](http://opensource.org/licenses/MIT)
