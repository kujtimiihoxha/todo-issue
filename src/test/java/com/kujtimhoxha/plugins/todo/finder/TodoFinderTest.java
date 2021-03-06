package com.kujtimhoxha.plugins.todo.finder;

import com.kujtimhoxha.plugins.todo.model.Todo;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * TodoFinderTest.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class TodoFinderTest {

    /**
     * Todo finder will find all 6 todos in the files.
     * @throws Exception
     */
    @Test
    public void testFind() throws Exception {
        final Set<File> files = new HashSet<File>();
        files.add(new File(System.getProperty("user.dir")+"/src/test/resources/finder/Test.html"));
        files.add(new File(System.getProperty("user.dir")+"/src/test/resources/finder/Test.java"));
        files.add(new File(System.getProperty("user.dir")+"/src/test/resources/finder/Test.js"));
        files.add(new File(System.getProperty("user.dir")+"/src/test/resources/finder/Test.pl"));
        files.add(new File(System.getProperty("user.dir")+"/src/test/resources/finder/Test.rb"));
        files.add(new File(System.getProperty("user.dir")+"/src/test/resources/finder/Test.scss"));
        final TodoFinder todoFinder = new TodoFinder(files);
        final List<Todo> todos=todoFinder.find();
        Assert.assertTrue(todos.size()==6);
    }
}