package com.kujtimhoxha.plugins.todo.filter;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * TypeFilterTest.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class TypeFilterTest {
    /**
     * Accepts file that has acceptable extension.
     * @throws Exception if something
     *  goes wrong.
     */
    @Test
    public void testAcceptTrue() throws Exception {
        final List<String> types = new ArrayList<String>();
        types.add(".java");
        final File testFile=new File(
                System.getProperty("user.dir")+"/src/test/resources/finder/Test.java"
        );
        Assert.assertTrue(
                new TypeFilter(types).accept(
                        testFile,
                        testFile.getName()
                )
        );
    }


    /**
     * Dies not accepts file that has unacceptable extension.
     * @throws Exception if something
     *  goes wrong.
     */
    @Test
    public void testAcceptFalse() throws Exception {
        final List<String> types = new ArrayList<String>();
        final File testFile=new File(System.getProperty("user.dir")+
                "/src/test/resources/finder/Test.pl");
        Assert.assertFalse(
                new TypeFilter(types).accept(
                        testFile,
                        testFile.getName()
                )
        );
    }
}