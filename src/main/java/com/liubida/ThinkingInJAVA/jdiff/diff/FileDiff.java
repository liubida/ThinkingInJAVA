package com.liubida.ThinkingInJAVA.jdiff.diff;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liubida.ThinkingInJAVA.jdiff.ColorString;
import com.liubida.ThinkingInJAVA.jdiff.Difference;

public class FileDiff {
    String[]         leftList;
    String[]         rightList;
    List<Difference> diffList;

    public FileDiff(String leftFile, String rightFile, int flag) {
        leftList = read(leftFile);
        rightList = read(rightFile);
        diffList = new Diff(leftList, rightList).diff();
        List<ColorString> leftCS = getLeft();
        List<ColorString> rightCS = getRight();

        for (int i = 0; i < 350; i++) {
            Formatter f = new Formatter(System.out);
            f.format(i + ": %-90s  " + i + ":%-90s\n", leftCS.get(i), rightCS.get(i));
        }
    }

    /**
     * getMap for left or right; LeftMap-> [DeletedStart, Difference];
     * RightMap->[AddedStart, Difference]
     * 
     * @param isLeft
     * @return
     */
    private Map<Integer, Difference> getMap(boolean isLeft) {
        Map<Integer, Difference> retMap = new HashMap<Integer, Difference>();
        for (Difference d : diffList) {
            if (isLeft) {
                retMap.put(d.getDeletedStart(), d);
            } else {
                retMap.put(d.getAddedStart(), d);
            }
        }
        return retMap;
    }

    /**
     * get the left string list
     * 
     * @return
     */
    public List<ColorString> getLeft() {
        Map<Integer, Difference> leftMap = getMap(true);
        List<ColorString> retList = new ArrayList<ColorString>();
        int delDiff = 0;
        int addDiff = 0;
        for (int i = 0; i < leftList.length; i++) {
            if (leftMap.containsKey(i)) {
                Difference d = leftMap.get(i);
                delDiff = d.getDeletedEnd() - d.getDeletedStart();
                addDiff = d.getAddedEnd() - d.getAddedStart();
                if (delDiff < 0) {
                    //delEnd = -1, something added, left should insert some blank lines
                    for (int j = 0; j <= addDiff; j++) {
                        retList.add(new ColorString(""));
                    }
                    retList.add(new ColorString(leftList[i]));
                } else {
                    if (addDiff < 0) {
                        //addEnd = -1, something deleted, left ignored, but must be colored
                        for (int j = 0; j <= delDiff; j++) {
                            retList.add(new ColorString(leftList[i + j], true));
                        }
                        i = i + delDiff;
                    } else {
                        //{del: [7, 8] add: [8, 10]} -> left 7-8 lines changes to right 8-10 lines
                        for (int j = 0; j <= delDiff; j++) {
                            retList.add(new ColorString(leftList[i + j], true));
                        }
                        i = i + delDiff;
                        for (int j = 0; j < addDiff - delDiff; j++) {
                            retList.add(new ColorString(""));
                        }
                    }
                }
            } else {
                retList.add(new ColorString(leftList[i]));
            }
        }
        return retList;
    }

    /**
     * get the right string list
     * 
     * @return
     */
    public List<ColorString> getRight() {
        Map<Integer, Difference> rightMap = getMap(false);
        List<ColorString> retList = new ArrayList<ColorString>();
        int delDiff = 0;
        int addDiff = 0;
        for (int i = 0; i < rightList.length; i++) {
            if (rightMap.containsKey(i)) {
                Difference d = rightMap.get(i);
                delDiff = d.getDeletedEnd() - d.getDeletedStart();
                addDiff = d.getAddedEnd() - d.getAddedStart();
                if (addDiff < 0) {
                    //addDiff = -1, something deleted, right should insert some blank lines
                    for (int j = 0; j <= delDiff; j++) {
                        retList.add(new ColorString(""));
                    }
                    retList.add(new ColorString(rightList[i]));
                } else {
                    if (delDiff < 0) {
                        //delEnd = -1, something added, right ignored, but must be colored
                        for (int j = 0; j <= addDiff; j++) {
                            retList.add(new ColorString(rightList[i + j], true));
                        }
                        i = i + addDiff;
                    } else {
                        //{del: [7, 8] add: [8, 10]} -> left 7-8 lines changes to right 8-10 lines
                        for (int j = 0; j <= addDiff; j++) {
                            retList.add(new ColorString(rightList[i + j], true));
                        }
                        i = i + addDiff;
                        for (int j = 0; j < delDiff - addDiff; j++) {
                            retList.add(new ColorString(""));
                        }
                    }
                }
            } else {
                retList.add(new ColorString(rightList[i]));
            }
        }
        return retList;
    }

    public FileDiff(String fromFile, String toFile) {
        String[] aLines = read(fromFile);
        String[] bLines = read(toFile);
        List<Difference> diffs = (new Diff(aLines, bLines)).diff();

        for (Difference d : diffs) {
            System.out.println(d);
        }
        //        Iterator it = diffs.iterator();
        //        while (it.hasNext()) {
        //            Difference diff = (Difference) it.next();
        //            int delStart = diff.getDeletedStart();
        //            int delEnd = diff.getDeletedEnd();
        //            int addStart = diff.getAddedStart();
        //            int addEnd = diff.getAddedEnd();
        //            String from = toString(delStart, delEnd);
        //            String to = toString(addStart, addEnd);
        //            String type = delEnd != Difference.NONE && addEnd != Difference.NONE ? "c"
        //                    : (delEnd == Difference.NONE ? "a" : "d");
        //
        //            System.out.println(from + type + to);
        //
        //            if (delEnd != Difference.NONE) {
        //                printLines(delStart, delEnd, "<", aLines);
        //                if (addEnd != Difference.NONE) {
        //                    System.out.println("---");
        //                }
        //            }
        //            if (addEnd != Difference.NONE) {
        //                printLines(addStart, addEnd, ">", bLines);
        //            }
        //        }
    }

    protected String toString(int start, int end) {
        // adjusted, because file lines are one-indexed, not zero.

        StringBuffer buf = new StringBuffer();

        // match the line numbering from diff(1):
        buf.append(end == Difference.NONE ? start : (1 + start));

        if (end != Difference.NONE && start != end) {
            buf.append(",").append(1 + end);
        }
        return buf.toString();
    }

    protected String[] read(String fileName) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            List<String> contents = new ArrayList<String>();
            String s;
            while ((s = in.readLine()) != null) {
                contents.add(s);
            }
            return (String[]) contents.toArray(new String[] {});
        } catch (Exception e) {
            System.err.println("error reading " + fileName + ": " + e);
            System.exit(1);
            return null;
        }
    }

    protected void printLines(int start, int end, String ind, List<String> lines) {
        for (int lnum = start; lnum <= end; ++lnum) {
            System.out.println(ind + " " + lines.get(lnum));
        }
    }

    public static void main(String[] args) {
        new FileDiff("./src/jdiff/GenyMove.txt", "./src/jdiff/GenyMove.d.txt");
//        new FileDiff("./src/jdiff/left", "./src/jdiff/right");
    }

}
