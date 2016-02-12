Maven plugin to convert TODO-s to Github/Gitlab issues. [![Build Status](https://travis-ci.org/kujtimiihoxha/todo-issue.svg?branch=master)](https://travis-ci.org/kujtimiihoxha/todo-issue)
------------------------------------------------------
### About
Todo-issue is a plugin that lets you easily write github issues while you are coding.This plugin enables the user to add comments in the source code that will be translated to github issues.This will help the developing team to keep track of the bugs and tasks assigned to specific users.
#### See it in action
To se a demo project clone [this](https://github.com/kujtimiihoxha/testing-todo-issue) repository and follow the instructions.
### Syntax
Todo issue from version 1.0.0 uses YAML script for issues.The yaml script is put inside the ```@todo``` and ```@end``` tags.
```yaml
    /*
    @todo
        title: Issue title
        body: |
            # Body of the issue.
            ### Markdown is also supported.
        assignee: kujtimiihoxha
        labels: java,todo
        milestone: v1.0.0
    @end
    */
```

- ```title``` : Is the title od the issue. **REQUIRED**
- ```body``` : Is the body of the issue, the text can be markdown formatted. **REQUIRED**
- ```assignee``` : To who is this issue assigned.
- ```labels``` : Labels of this issue.
- ```milestone``` : Milestone title of this issue.

Make sure that the yaml script formatting is met or the plugin will throw parsing error, 
```title```,```body```,```assignee```,```labels``` and ```milestone``` must have the same white space before them, and the body content
must be spaced one tab inside the body tag.
This is not allowed :x: 
```yaml
    /*
    @todo
        title: Issue title
        body: |
            # Body of the issue.
            ### Markdown is also supported.
    assignee: kujtimiihoxha
        labels: java,todo
    milestone: v1.0.0
    @end
    */
```
This is not allowed :x: 
```yaml
    /*
    @todo
        title: Issue title
        body: |
        # Body of the issue.
        ### Markdown is also supported.
        assignee: kujtimiihoxha
        labels: java,todo
        milestone: v1.0.0
    @end
    */
```

### Configuration file todo.json
Before you can run the ```find``` goal you must add the file todo.json to the base directory(where the pom.xml is if you ar using maven).
Remember to add todo.json to the .gitignore file because it contains your access token.
```json
    {
      "git-server":"Github",
      "gitlab-url":"https://gitlab.com/",
      "repository-username":"kujtimiihoxha",
      "issuer-username":"kujtimiihoxha",
      "token":"token",
      "repository":"todo-issue",
      "file-link":true
    }
```

 - ```git-server``` : can be "Github" or "Gitlab".
 - ```gitlab-url``` : if you are using "Gitlab" you must provide the gitlab server url.
 - ```repository-username``` : the repository user.
 - ```issuer-username``` : the user who is writing the issues.
 - ```token``` : the user access token.
 - ```repository``` : the repository to post issues to .
 - ```file-link``` : if set to false it will not add the file and line link to the end of the issue, by default it is true .
 
### Plugin configurations
- To execute ```find``` goal add the plugin to your build tag.
```xml
    <build>
        <plugins>
            <plugin>
                <groupId>com.kujtimhoxha.plugins</groupId>
                <artifactId>todo-issue-plugin</artifactId>
                <version>1.0.0</version>
                <configuration>
                 <sources>
                   <source>{source folder path }</source>
                   <source>{any other folder path }</source>
                 </sources>
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
With this configurations the plugin will search for todo-s in every build by runing.
```bash
mvn clean install
```

- Or you can add a dedicated profile for the plugin 
```xml
<profiles>
    <profile>
      <id>todo</id>
      <build>
        <plugins>
          <plugin>
            <groupId>com.kujtimhoxha.plugins</groupId>
            <artifactId>todo-issue-plugin</artifactId>
            <version>1.0.0-SNAPSHOT</version>
            <configuration>
            <sources>
              <source>{source folder path }</source>
              <source>{any other folder path }</source>
            </sources>
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
    </profile>
<profiles>
```
Now you can run the plugin by executing :
```bash
mvn clean install -Ptodo
```
- You can also run todo-issue from the commandline and just add it to your dependencies 
```xml
    <dependencies>
        <dependency>
            <groupId>com.kujtimhoxha.plugins</groupId>
            <artifactId>todo-issue-plugin</artifactId>
            <version>0.4</version>
        </dependency>
    </dependencies>
```
Run:
```bash 
    mvn clean com.kujtimhoxha.plugins:todo-issue-plugin:find -Dsources={source folder path },{any other folder path }
```
### Other plugin configurations 
- **Excludes** : Folders/Files to exclude from the search.
```xml
    <configuration>
        <sources>
          <source>{source folder path }</source>
          <source>{any other folder path }</source>
        </sources>
        <excludes>
            <exclude>{folder/file path to exclude}</exclude>
            <exclude>{another folder/file path to exclude}</exclude>
        </excludes>
    </configuration>
```

- **Config** : Specify the path of the todo.json file, by default it will look for todo.json in the base directory of the project.
```xml
    <configuration>
        <sources>
          <source>{source folder path }</source>
          <source>{any other folder path }</source>
        </sources>
        <config>{new path}/todo.json</config>
    </configuration>
```

- **Types** : File types to search for todos, by default it only searches in ```.java``` files you can change this property and override the dfault.
```xml
    <configuration>
        <sources>
          <source>{source folder path }</source>
          <source>{any other folder path }</source>
        </sources>
        <types>
            <type>.java</type>
            <type>.html</type>
            <type>.js</type>
        </types>    
    </configuration>
```

### Contribute

To help improve todo-issue clone this repo and submit pull requests with suggested changes.<br>
Add unit tests for new features added and/or missing tests.<br>
Please test your changes before sending a pull request by running :<br>
```bash
 mvn clean install 
```   
### Licence

The todo-issue is open-sourced software licensed under the [MIT license](http://opensource.org/licenses/MIT)