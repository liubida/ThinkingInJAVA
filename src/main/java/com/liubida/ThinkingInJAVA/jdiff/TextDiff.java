package com.liubida.ThinkingInJAVA.jdiff;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liubida
 */
public class TextDiff {
    private String[]         leftList;
    private String[]         rightList;
    private List<Difference> diffList;

    private final String     DIFF_BLANK_CHAR = ""; //characters for inserted blank lines

    public TextDiff(String left, String right, String split) {
        leftList = ReadString(left, split);
        rightList = ReadString(right, split);
        //        diffList = new Diff(leftList, rightList).diff();
        MyDiff m = new MyDiff(leftList, rightList);
        m.PrintMatch();
        m.PrintMaxPoint();
        m.PrintMinDepth();
        diffList = m.diff();
        for (Difference d : diffList) {
            System.out.println(d);
        }

        List<ColorString> leftCS = getLeft();
        List<ColorString> rightCS = getRight();

        for (int i = 0; i < leftCS.size(); i++) {
            Formatter f = new Formatter(System.out);
            f.format(i + ": %-90s  " + i + ":%-90s\n", leftCS.get(i), rightCS.get(i));
        }
    }

    public TextDiff(String leftFile, String rightFile) {
        leftList = ReadFile(leftFile);
        rightList = ReadFile(rightFile);
        MyDiff m = new MyDiff(leftList, rightList);

        diffList = m.diff();
        for (Difference d : diffList) {
            System.out.println(d);
        }

        List<ColorString> leftCS = getLeft();
        List<ColorString> rightCS = getRight();

        System.out.println(leftCS.size());
        System.out.println(rightCS.size());

        for (int i = 0; i < leftCS.size(); i++) {
            Formatter f = new Formatter(System.out);
            f.format(i + ": %-90s  " + i + ":%-90s\n", leftCS.get(i), rightCS.get(i));
        }
    }

    public static void main(String[] args) {
        new TextDiff("./src/jdiff/GenyMove.txt", "./src/jdiff/GenyMove.d.txt");
        //        new TextDiff("./src/jdiff/left", "./src/jdiff/right");
        //        new TextDiff("b.c.x.c.a.d.f.e.s.b.a.b.c.a.c.a", "a.b.c.a.c.a.d.f", "\\.");
    }

    private String[] ReadString(String str, String split) {
        return str.split(split);
    }

    private String[] ReadFile(String fileName) {
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
                        retList.add(new ColorString(DIFF_BLANK_CHAR));
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
                            retList.add(new ColorString(DIFF_BLANK_CHAR));
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
                        retList.add(new ColorString(DIFF_BLANK_CHAR));
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
                            retList.add(new ColorString(DIFF_BLANK_CHAR));
                        }
                    }
                }
            } else {
                retList.add(new ColorString(rightList[i]));
            }
        }
        return retList;
    }
}
