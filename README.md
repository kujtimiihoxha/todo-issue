Maven plugin to convert TODO-s to Github/Gitlab issues. 
------------------------------------------------------
[![Build Status](https://travis-ci.org/kujtimiihoxha/todo-issue.svg?branch=master)](https://travis-ci.org/kujtimiihoxha/todo-issue) [![Build status](https://ci.appveyor.com/api/projects/status/k3du9ep4g0n93h04?svg=true)](https://ci.appveyor.com/project/kujtimiihoxha/todo-issue)

### About
Todo-issue is a plugin that lets you easily write github issues while you are coding.This plugin enables the developer 
to add comments in the source code that will be translated to github issues.After the issues are closed in github/gitlab 
the todo-s will be removed from the source automatically in the next execution of the find goal.This will help the developing team to keep track of the bugs and tasks assigned to specific users.
#### See it in action
To se a demo project clone [this](https://github.com/kujtimiihoxha/testing-todo-issue) repository and follow the instructions.
### Syntax
Todo issue from version 1.0.0 uses [YAML](http://yaml.org/) human friendly data serialization standard for issues.The yaml script is put inside the ```@todo``` and ```@end``` tags.
```yaml
    /*
    @todo
        title: Issue title
        body: |
            # Body of the issue.
            ### Markdown is also supported.
        assignee: kujtimiihoxha
        labels: java,todo
        milestone: v1.0.6
    @end
    */
```

- ```title``` : Is the title od the issue. **REQUIRED**
- ```body``` : Is the body of the issue, the text can be markdown formatted. **REQUIRED**
- ```assignee``` : Who is assigned to this issue.
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
    milestone: v1.0.6
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
        milestone: v1.0.6
    @end
    */
```
If you are using [Intellij](https://www.jetbrains.com/idea/) you can use these [settings](http://kujtimhoxha.com/wp-content/uploads/2016/02/settings.zip) to add a live template to make it easy to write todo comments, unzip the archive and import the settings to Intellij.   
This is how it would look like :   

![Todo](http://kujtimhoxha.com/content/images/2016/02/todo-issue.gif)

Just start writing ```@todo``` and hit ```enter``` then Intellij will automatically add the todo.

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
                <version>1.0.6</version>
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
            <version>1.0.6</version>
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
            <version>1.0.6</version>
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

# Usage with Gradle
Although  todo-issue is a maven plugin if needed todo-issue can be used in gradle (Android development projects).
For use with gradle todo-issue has a special GradleRunner that will be used to run with gradle you must setup the build.gradle
to use todo-issue.

- Add buildscript : 
```
    buildscript {
        repositories {
           mavenCentral()
        }
        dependencies {
            classpath "com.kujtimhoxha.plugins:todo-issue-plugin:1.0.6"
        }
    }
```

- Add maven repository and todo-issue dependency to your project :
```
    repositories {
        mavenCentral()
    }
    dependencies {
        compile "com.kujtimhoxha.plugins:todo-issue-plugin:1.0.6"
    }
```
- Add find task :
```
    task find(){
        def sources = new ArrayList<File>();
        sources.add(new File({source path}));
        sources.add(new File({another source path}));
        def todo= new  com.kujtimhoxha.plugins.todo.GradleRunner(sources)
        todo.run()
    }
```
- If you need more configuration just use other constructors :
```java 
    GradleRunner(final List<File> sources,final List<File> excludes)
    GradleRunner(final List<File> sources, final List<File> excludes,final File config,final List<String> types)
```

The defaults for this parameters are the same as the maven defaults.<br>
To run this task just execute :

```
    gradle task:find
```

### Run on every build
To run find on every build just add it to the build dependencies.
```
    build{
        dependsOn find
    }
```

Then when you execute :
```
    gradle build 
```
find task will be executed automatically.

You can find a demo gradle project in [this](https://github.com/kujtimiihoxha/gradle-todo-issue) repository.
## Usage with Visual Studio Projects
Todo-issue can also be used with visual studio projects but you need to have Java and Maven installed.   
To install java follow [this](http://kujtimhoxha.com/2015/09/16/installing-the-java-jdk/) tutorial and to install maven follow [this](http://www.mkyong.com/maven/how-to-install-maven-in-windows/) tutorial .
To use todo-issue with your Visual Studio project you need to add a simple pom.xml file to your project where your .sln file is in this pom write :
```xml
    <project>
      <modelVersion>4.0.0</modelVersion> 
      <groupId>your group id </groupId>   <!--can be tour domain name like com.kujtimhoxha-->
      <artifactId>your artifact name </artifactId>  <!--application name -->
      <version>1</version>

        <build>
            <plugins>
                <plugin>
                    <groupId>com.kujtimhoxha.plugins</groupId>
                    <artifactId>todo-issue-plugin</artifactId>
                    <version>1.0.6</version>
                    <configuration>
                        <sources>
                            <source>${source-1}</source>
                            <source>${source-2}</source>
                        </sources>
                        <types>
                        <type>.cs</type>
                        </types>
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
        
    </project>
```

It is recommended to exclude unnecessary folders like the bin, obj and other folder that do not contain source code, in .net application 
it is recommended to exclude the scripts folder if you have many third party scripts because this will make the process of reading the files faster.
How to exclude folders and files is described [here](https://github.com/kujtimiihoxha/todo-issue#other-plugin-configurations).
Then you need to add a todo.json file in the same place, the json file is as described [above](https://github.com/kujtimiihoxha/todo-issue#configuration-file-todojson).   

Now to execute this plugin in every build you need to we need to create a custom .bat file and place it where we put the pom.xml and todo.json
```batch

SET JAVA_HOME={Path to java home}
cd {Path to your project}
call {Path to maven home}\bin\mvn clean install

```

Save this file as todo.bat go to your project properties and go to build event and add this line to your post-build events
```batch
call {Path to your project}\todo.bat
```

Now on every build the plugin will search for todo's in your .cs files.

### Contribute

To help improve todo-issue clone this repo and submit pull requests with suggested changes.<br>
Add unit tests for new features added and/or missing tests.<br>
Please test your changes before sending a pull request by running :<br>
```bash
 mvn clean install 
```   

### Dependencies 
- [Google Http Client](https://github.com/google/google-http-java-client)
- [SnakeYaml](https://bitbucket.org/asomov/snakeyaml)
- [Maven plugins](https://github.com/apache/maven-plugins)

### Licence

Todo-issue is open-sourced software licensed under the [MIT license](http://opensource.org/licenses/MIT)
