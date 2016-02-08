package com.kujtimhoxha.maven.filter;

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
     * Returns true if the file extension is in the extensions list.
     * @throws Exception
     */
    @Test
    public void testAcceptFileType() throws Exception {
        String[] types=new String[1];
        types[0]=".java";
        Assert.assertTrue(
            "Type filter should return true if the file extension is in the list",
            new TypeFilter(types)
                .accept(
                    new File(
                          System.getProperty("user.dir")+"/src/main/java/com/kujtimhoxha/maven/Find.java"
                    ),
                    new File(
                         System.getProperty("user.dir")+"/src/main/java/com/kujtimhoxha/maven/Find.java"
                    ).getName()
                )
        );
    }
    /**
     * Returns false if the file extension is not in the extensions list.
     * @throws Exception
     */
    @Test
    public void testDoesNotAcceptFileType() throws Exception {
        String[] types=new String[1];
        types[0]=".json";
        Assert.assertFalse(
            "Type filter should return false if the file extension is not in the list",
            new TypeFilter(types)
                .accept(
                    new File(
                            System.getProperty("user.dir")+"/src/main/java/com/kujtimhoxha/maven/Find.java"
                    ),
                    new File(
                            System.getProperty("user.dir")+"/src/main/java/com/kujtimhoxha/maven/Find.java"
                    ).getName()
                )
        );
    }
}