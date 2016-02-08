package com.kujtimhoxha.maven.filter;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
/**
 * FolderFilterTest.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class FolderFilterTest {

    /**
     * Will return false if the folder does not exist
     * @throws Exception
     */
    @Test
    public void testAcceptFolderDoesNotExist() throws Exception {
        List<String> folders=new ArrayList<String>();
        folders.add("test/");
        Assert.assertFalse(
            "Folder filter should return false if folder does not exist",
            new FolderFilter(folders)
                .accept(
                    new File(folders.get(0)),
                    new File(folders.get(0)).getName()
                )
        );
    }
    /**
     * Will return false if the folder is in the excluded list
     * @throws Exception
     */
    @Test
    public void testAcceptFolderIsExcluded() throws Exception {
        List<String> folders=new ArrayList<String>();
        folders.add(System.getProperty("user.dir")+"/src/main/java/com/kujtimhoxha/maven/");
        Assert.assertFalse(
            "Folder filter should return false if folder is excluded",
            new FolderFilter(folders)
                .accept(
                    new File(folders.get(0)),
                    new File(folders.get(0)).getName()
                )
        );
    }
    /**
     * Will return true if the folder exists and is not in the excluded list
     * @throws Exception
     */
    @Test
    public void testAcceptFolderIsNotExcluded() throws Exception {
        List<String> folders=new ArrayList<String>();
        Assert.assertTrue(
            "Folder filter should return true if folder exists and is not excluded",
            new FolderFilter(folders)
                .accept(
                    new File(
                        System.getProperty("user.dir")+"/src/main/java/com/kujtimhoxha/maven/"
                    ),
                    new File(
                        System.getProperty("user.dir")+"/src/main/java/com/kujtimhoxha/maven/"
                    ).getName()
                )
        );
    }
}