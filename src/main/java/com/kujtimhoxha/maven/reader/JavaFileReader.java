/**
 * Copyright (c) 2016 Kujtim Hoxha
 * <p>
 * Permission is hereby granted, free of charge,
 * to any person obtaining a copy of this software
 * and associated documentation files (the "Software"),
 * to deal in the Software without restriction,
 * including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit
 * persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice
 * shall be included in all copies or substantial
 * portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF
 * ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 * TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT
 * SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR
 * ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN
 * ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE
 * OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.kujtimhoxha.maven.reader;

import com.google.api.client.util.Lists;
import com.kujtimhoxha.maven.base.Reader;
import com.kujtimhoxha.maven.filter.TypeFilter;
import com.kujtimhoxha.maven.matcher.JavaTodoFinder;
import com.kujtimhoxha.maven.model.Todo;
import org.apache.maven.plugin.MojoExecutionException;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * JavaFileReader.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class JavaFileReader implements Reader {
    private final List<File> accepted =new ArrayList<File>();

    public JavaFileReader(List<File> files) {
        final String[] extension={".java"};
        final TypeFilter filter=new TypeFilter(extension);
        for(File file: files){
            if(filter.accept(file,file.getName())){
                accepted.add(file);
            }
        }
    }
    @Override
    public List<Todo> todos() throws IOException {
        List<Todo> todos=new ArrayList<Todo>();
        for(File file: accepted){
            String result=new JavaTodoFinder().find(content(file));
            if(result!=null) {
                String[] comments =result.split("\\*");
                if (comments.length > 0) {
                    todos.add(new Todo(file, Arrays.asList(comments)));
                }
            }
        }
        return todos;
    }
    public String content(File file) throws IOException {
        final StringBuilder comment = new StringBuilder("");
        String line;
        final InputStream fis = new FileInputStream(file);
        final InputStreamReader isr = new InputStreamReader(fis,
                Charset.forName("UTF-8"));
        BufferedReader br = new BufferedReader(isr);

        while ((line = br.readLine()) != null) {
            comment.append(line.replaceAll("^\\s+","")).append("\n");
        }
        br.close();
        return comment.toString();
    }


}
