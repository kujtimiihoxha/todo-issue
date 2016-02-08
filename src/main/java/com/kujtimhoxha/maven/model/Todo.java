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
package com.kujtimhoxha.maven.model;

import java.io.File;
import java.util.List;

/**
 * Todo.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Todo {
    /**
     * [ToDo] $ First todo submitted [issue=#15]$
     * #/
     * # This is a automated issue
     * ## Test
     *   - List1
     *   - List2
     *
     * ### Code
     *  ```java
     *     public Todo(File file, List<String> comments) {
     *          this.file=file;
     *          this.comments=comments;
     *     }
     *  ```
     *  #/
     *  %label1,label2,label3%
     *  ~kujtimiihoxha~
     */
    private File file;
    /**
     * todo test
     */
    private List<String> comments;

    public Todo(File file, List<String> comments) {
        this.file=file;
        this.comments=comments;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public List<String> getComments() {
        return comments;
    }
    public void setComments(List<String> comments) {
        this.comments = comments;
    }
}
