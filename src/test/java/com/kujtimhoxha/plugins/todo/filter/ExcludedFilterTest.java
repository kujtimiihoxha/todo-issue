package com.kujtimhoxha.plugins.todo.filter;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * ExcludedFilterTest.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class ExcludedFilterTest {

    /**
     * Accepts file that is not excluded.
     * @throws Exception if something
     *  goes wrong.
     */
    @Test
    public void testAcceptTrue() throws Exception {
        final List<File> excludes = new ArrayList<File>();
        excludes.add(new File(System.getProperty("user.dir")+"/src/test"));
        excludes.add(new File(System.getProperty("user.dir")+"/src/main"));
        final File testFile=new File(System.getProperty("user.dir")+"/.travis.yml");
        Assert.assertTrue(
            new ExcludedFilter(excludes).accept(
                testFile,
                testFile.getName()
            )
        );
    }


    /**
     * Does not accepts file that is excluded.
     * @throws Exception if something
     *  goes wrong.
     */
    @Test
    public void testAcceptFalse() throws Exception {
        final List<File> excludes = new ArrayList<File>();
        excludes.add(new File(System.getProperty("user.dir")+"/src/test"));
        excludes.add(new File(System.getProperty("user.dir")+"/src/main"));
        excludes.add(new File(System.getProperty("user.dir")+"/.travis.yml"));
        Assert.assertFalse(
                new ExcludedFilter(excludes).accept(
                        excludes.get(2),
                        excludes.get(2).getName()
                )
        );
    }
}