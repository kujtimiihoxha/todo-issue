package com.kujtimhoxha.plugins.filter;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

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
        final List<String> excludes = new ArrayList<String>();
        excludes.add(".java");
        final File testFile=new File(System.getProperty("user.dir")+"/src/main/java/com/kujtimhoxha/plugins/Find.java");
        Assert.assertTrue(
                new TypeFilter(excludes).accept(
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
        final List<String> excludes = new ArrayList<String>();
        final File testFile=new File(System.getProperty("user.dir")+"/.travis.yml");
        Assert.assertFalse(
                new TypeFilter(excludes).accept(
                        testFile,
                        testFile.getName()
                )
        );
    }
}