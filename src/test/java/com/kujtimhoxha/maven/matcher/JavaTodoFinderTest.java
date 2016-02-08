package com.kujtimhoxha.maven.matcher;

import org.junit.Test;

/**
 * JavaTodoFinderTest.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class JavaTodoFinderTest {

    @Test
    public void testFind() throws Exception {
        System.out.println(new JavaTodoFinder().find("/**\n" +
                " * Copyright (c) 2016 Kujtim Hoxha\n" +
                " *\n" +
                " * Permission is hereby granted, free of charge,\n" +
                " * to any person obtaining a copy of this software\n" +
                " * and associated documentation files (the \"Software\"),\n" +
                " * to deal in the Software without restriction,\n" +
                " * including without limitation the rights to use,\n" +
                " * copy, modify, merge, publish, distribute, sublicense,\n" +
                " * and/or sell copies of the Software, and to permit\n" +
                " * persons to whom the Software is furnished to do so,\n" +
                " * subject to the following conditions:\n" +
                " *\n" +
                " * The above copyright notice and this permission notice\n" +
                " * shall be included in all copies or substantial\n" +
                " * portions of the Software.\n" +
                " *\n" +
                " * THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF\n" +
                " * ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED\n" +
                " * TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A\n" +
                " * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT\n" +
                " * SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR\n" +
                " * ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN\n" +
                " * ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,\n" +
                " * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE\n" +
                " * OR OTHER DEALINGS IN THE SOFTWARE.\n" +
                " */\n" +
                "package com.kujtimhoxha.maven.matcher;\n" +
                "\n" +
                "import com.kujtimhoxha.maven.base.Finder;\n" +
                "\n" +
                "import java.util.regex.Matcher;\n" +
                "import java.util.regex.Pattern;\n" +
                "\n" +
                "/**\n" +
                " * BodyFinder.\n" +
                " *\n" +
                " * @author Kujtim Hoxha (kujtimii.h@gmail.com)\n" +
                " * @version $Id$\n" +
                " * @since 0.1\n" +
                " */\n" +
                "public class BodyFinder implements Finder {\n" +
                "\n" +
                "    /**\n" +
                "     * TODO Pattern for Body in comment.\n" +
                "     */\n" +
                "    private static final Pattern PATTERN = Pattern.compile(\"#/(.*?)#/\");\n" +
                "\n" +
                "    public String find(String content) {\n" +
                "        final Matcher matcher = PATTERN.matcher(content.replaceAll(\"\\\\n\",\"***\"));\n" +
                "        if(matcher.find())\n" +
                "        {\n" +
                "            return matcher.group(1).replaceAll(\"\\\\*\\\\*\\\\*\",\"\\n\");\n" +
                "        }\n" +
                "        return null;\n" +
                "    }\n" +
                "}\n"));
    }
}