package com.kujtimhoxha.plugins.matcher;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * IssueFinderTest.
 *
 * @author Kujtim Hoxha (kujtimii.h@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class IssueFinderTest {
    /*
    @issue
    title: My title
    body: |
      # This is the test
            - Hello
            - There
      Testing the new yaml
    assignee: kujtimiihoxha
    labels : hello,qwe,qwe
    milestone : 1
    @end
     */
    @Test
    public void testFind() throws Exception {
        IssueFinder issueFinder=new IssueFinder();
        issueFinder.find("    /*\n" +
                "    @issue\n" +
                "    title: My title\n" +
                "    body: |\n" +
                "      # This is the test\n" +
                "            - Hello\n" +
                "            - There\n" +
                "      Testing the new yaml\n" +
                "    assignee: kujtimiihoxha\n" +
                "    labels : hello,qwe,qwe\n" +
                "    milestone : 1\n" +
                "    @end\n" +
                "     */");
    }
}