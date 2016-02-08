package com.kujtimhoxha.plugins.filter;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * FileFilterTest.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class FileFilterTest {

    /**
     * Will return false if the file does not exist
     * @throws Exception
     */
    @Test
    public void testAcceptFileDoesNotExist() throws Exception {
        List<String> files=new ArrayList<String>();
        files.add("test.java");
        Assert.assertFalse(
                "File filter should return false if file does not exist",
                new FileFilter(files)
                    .accept(
                        new File(files.get(0)),
                        new File(files.get(0)).getName()
                    )
        );
    }
    /**
     * Will return false if the file is in the excluded list
     * @throws Exception
     */
    @Test
    public void testAcceptFileIsExcluded() throws Exception {
        List<String> files=new ArrayList<String>();
        files.add(System.getProperty("user.dir")+"/src/main/java/com/kujtimhoxha/plugins/Find.java");
        Assert.assertFalse(
                "File filter should return false if file is excluded",
                new FileFilter(files)
                    .accept(
                        new File(files.get(0)),
                        new File(files.get(0)).getName()
                    )
        );
    }
    /**
     * Will return true if the file exists and is not in the excluded list
     * @throws Exception
     */
    @Test
    public void testAcceptFileIsNotExcluded() throws Exception {
        List<String> files=new ArrayList<String>();
        Assert.assertTrue(
                "File filter should return true if file exists and is not excluded",
                new FileFilter(files)
                    .accept(
                        new File(
                            System.getProperty("user.dir")+"/src/main/java/com/kujtimhoxha/plugins/Find.java"
                        ),
                        new File(
                            System.getProperty("user.dir")+"/src/main/java/com/kujtimhoxha/plugins/Find.java"
                        ).getName()
                    )
        );
    }
}