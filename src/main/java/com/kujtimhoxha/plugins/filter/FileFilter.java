/**
 * Copyright (c) 2016 Kujtim Hoxha
 *
 * Permission is hereby granted, free of charge,
 * to any person obtaining a copy of this software
 * and associated documentation files (the "Software"),
 * to deal in the Software without restriction,
 * including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit
 * persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice
 * shall be included in all copies or substantial
 * portions of the Software.
 *
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
package com.kujtimhoxha.plugins.filter;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Paths;
import java.util.List;

/**
 * FileFilter.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class FileFilter implements FilenameFilter {
    private  final List<String>  files;
    public FileFilter(final List<String> files) {
        for(int i=0;i<files.size();i++) {
            files.set(i, Paths.get(files.get(i)).toString()) ;
        }
        this.files = files;
    }
    public boolean accept(File dir, String name) {
        if(dir.exists()){
            for(String exc:this.files){
                if(dir.getAbsolutePath().equals(exc)){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
