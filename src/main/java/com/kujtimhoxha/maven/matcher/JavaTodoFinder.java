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
package com.kujtimhoxha.maven.matcher;

import com.kujtimhoxha.maven.base.Finder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * JavaTodoFinder.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class JavaTodoFinder implements Finder{

    /**
     * Pattern for Body in comment.
     */
    private static final Pattern PATTERN = Pattern.compile("/\\**(.*?)\\*/");

    @Override
    public String find(String content) {
        final Matcher matcher = PATTERN.matcher(content.replaceAll("\\n","----"));
        int matches=0;
        StringBuilder response = new StringBuilder("");
        while (matcher.find())
        {
            if(matcher.group(1).toLowerCase().contains("[todo]")) {
                if (matches == 0) {
                    response.append(matcher.group(1).replaceAll("\\* ", "")
                            .replaceAll("----", "\n").replaceAll("\\*", ""));
                } else {
                    response.append("*").append(matcher.group(1).replaceAll("\\* ", "")
                            .replaceAll("----", "\n").replaceAll("\\*", ""));
                }
                matches++;
            }
        }
        if(response.toString().isEmpty())
            return null;
        return response.toString();
    }
    public String findForClosed(String content,String id) {
        final Matcher matcher = PATTERN.matcher(content.replaceAll("\\n","----"));
        int matches=0;
        StringBuilder response = new StringBuilder("");
        while (matcher.find())
        {
            if(matcher.group(1).toLowerCase().contains("[todo]") &&
                    matcher.group(1).toLowerCase().contains(String.format("[issue=#%s]",id))) {
                if (matches == 0) {
                    response.append(matcher.group(0).replaceAll("----", "\n"));
                } else {
                    response.append("^?^").append(matcher.group(0).replaceAll("----", "\n"));
                }
                matches++;
            }
        }
        if(response.toString().isEmpty())
            return null;
        return response.toString();
    }
}
