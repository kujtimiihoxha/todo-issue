package com.kujtimhoxha.plugins.todo.finder;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * FileFinderTest.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class FileFinderTest {

    /**
     * Tests if the File finder finds all files that meet
     *  the configurations.
     * @throws Exception if something goes wrong.
     */
    @Test
    public void testFind() throws Exception {
        final List<File> sources=new ArrayList<File>();
        sources.add(new File(System.getProperty("user.dir")+"/src/test/resources/finder"));
        sources.add(new File(System.getProperty("user.dir")+"/src/test/resources/languages"));
        sources.add(new File(System.getProperty("user.dir")+"/src/test/resources/matcher/TestTodoPattern.java"));
        final List<String> types=new ArrayList<String>();
        types.add(".java");
        types.add(".js");
        final List<File> excludes=new ArrayList<File>();
        excludes.add(new File(System.getProperty("user.dir")+"/src/test/resources/languages/test.js"));
        final FileFinder finder=new FileFinder(sources,excludes,types);
        Assert.assertTrue(finder.find().size()==3);
    }

    /**
     * Tests if the File finder has duplicate files.
     * @throws Exception if something goes wrong.
     */
    @Test
    public void testDuplicate() throws Exception {
        final List<File> sources=new ArrayList<File>();
        sources.add(new File(System.getProperty("user.dir")+"/src/test/resources/finder"));
        sources.add(new File(System.getProperty("user.dir")+"/src/test/resources/finder/Test.java"));
        final List<String> types=new ArrayList<String>();
        types.add(".java");
        final List<File> excludes=new ArrayList<File>();
        final FileFinder finder=new FileFinder(sources,excludes,types);
        System.out.println(finder.find().size());
        Assert.assertTrue(finder.find().size()==1);
    }

}