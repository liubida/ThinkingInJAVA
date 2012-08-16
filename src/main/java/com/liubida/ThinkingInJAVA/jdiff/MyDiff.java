/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-7-11
 * $Id$
 * 
 * Copyright 1999-2100 Alibaba.com Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Alibaba Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Alibaba.com.
 */
package com.liubida.ThinkingInJAVA.jdiff;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liubida
 */

public class MyDiff {
    private Map<Integer, String> leftMap       = new HashMap<Integer, String>();
    private Map<Integer, String> rightMap      = new HashMap<Integer, String>();
    private List<Difference>     diffList      = new ArrayList<Difference>();
    private int                  height;
    private int                  width;
    /**
     * 匹配矩阵，match[i][j]==1: left的第i行和right的第j行相同 如何从表格中的左上角，找到一条路径，满足： 1.
     * 经过的值为"1"的单元格最多； 2. 每次只能向右，或者向下，或者向右下方移动一格； 3.
     * 如果本次位置在值为"1"的格子上，只能向右下方移动一格； 4. 如果移动到右边界或者下边界外，则终止。
     */
    private int[][]              match;
    /**
     * 最大匹配点数矩阵，maxPoint[i][j]:区域[i][j]所含有的匹配点数
     */
    private int[][]              maxPoint;
    /**
     * 最小路径矩阵，minDepth[i][j]:从点[i][j]开始，匹配到矩阵边界后， 在left方向上的最短路径长度
     */
    private int[][]              minDepth;

    /**
     * DOWN: 向下 DOWNRIGHT： 右下 RIGHT：向右
     */
    private final int            DOWN          = -1;
    private final int            DOWNRIGHT     = 0;
    private final int            RIGHT         = 1;

    private final int            STARTCOUNT    = 0;
    private int                  countL        = 0;
    private int                  countR        = 0;

    private int                  lastDirection = DOWNRIGHT;

    private void GOTOEnd(int direction, int l, int r) {
        if (DOWNRIGHT == lastDirection) {
            diffList.add(new Difference(l, l, r - 1, r - 1));
        } else {
            if (DOWN == lastDirection) {
                if (countR > 0) {
                    diffList.add(new Difference(l - countL, l, r - 1 - countR, r - 1 - 1));
                } else {
                    diffList.add(new Difference(l - countL, l, r - 1, -1));
                }
            } else {
                if (countL > 0) {
                    diffList.add(new Difference(l - countL, l, r - 1 - countR, r - 1 - 1));
                } else {
                    diffList.add(new Difference(l, -1, r - 1 - countR, r - 1 - 1));
                }
            }
        }
    }

    private void travse(int direction, int l, int r) {
        if (DOWN == direction) {
            countL++;
        } else {
            if (RIGHT == direction) {
                countR++;
            } else {
                if (DOWNRIGHT == lastDirection) {
                    countL = STARTCOUNT;
                    countR = STARTCOUNT;
                } else {
                    if (DOWN == lastDirection) {
                        if (countR > 0) {
                            diffList.add(new Difference(l - 1 - countL, l - 1 - 1,
                                    r - 1 - countR >= 0 ? r - 1 - countR : 0, r - 1 - 1));
                        } else {
                            diffList.add(new Difference(l - 1 - countL, l - 1 - 1, r - 1, -1));
                        }
                    } else {
                        if (countL > 0) {
                            diffList.add(new Difference(l - 1 - countL >= 0 ? l - 1 - countL : 0,
                                    l - 1 - 1, r - 1 - countR, r - 1 - 1));
                        } else {
                            diffList.add(new Difference(l - 1, -1, r - 1 - countR, r - 1 - 1));
                        }
                    }
                    countL = STARTCOUNT;
                    countR = STARTCOUNT;
                }
            }
        }
        lastDirection = direction;

        if (l >= height - 1) {
            if (r < width - 1) {
                //到达下边界，向右，插入
                travse(RIGHT, l, r + 1);
                //                diffList.add(new Difference(l, -1, r, width - 1));
            } else {
                //到达右下角顶点
                if (match[l][r] > 0) {
                    travse(DOWNRIGHT, l + 1, r + 1);
                } else {
                    GOTOEnd(RIGHT, l, r + 1);
                }
            }
        } else {
            if (r >= width - 1) {
                //到达右边界，向下，刪除
                travse(DOWN, l + 1, r);
                //                diffList.add(new Difference(l, height - 1, r, -1));
            } else {
                //在中间
                if (match[l][r] > 0) {
                    //匹配，朝右下方向走
                    travse(DOWNRIGHT, l + 1, r + 1);
                } else {
                    if (maxPoint[l + 1][r] > maxPoint[l][r + 1]) {
                        travse(DOWN, l + 1, r);
                    }
                    if (maxPoint[l + 1][r] < maxPoint[l][r + 1]) {
                        travse(RIGHT, l, r + 1);
                    }
                    if (maxPoint[l + 1][r] == maxPoint[l][r + 1]) {
                        if (minDepth[l + 1][r] <= minDepth[l][r + 1]) {
                            travse(DOWN, l + 1, r);
                        } else {
                            travse(RIGHT, l, r + 1);
                        }
                    }
                }
            }
        }
    }

    public List<Difference> diff() {
        if (match[0][0] > 0) {
            travse(DOWNRIGHT, 0, 0);
        } else {
            int l = 0, r = 0;
            if (maxPoint[l + 1][r] > maxPoint[l][r + 1]) {
                travse(DOWN, l + 1, r);
            }
            if (maxPoint[l + 1][r] < maxPoint[l][r + 1]) {
                travse(RIGHT, l, r + 1);
            }
            if (maxPoint[l + 1][r] == maxPoint[l][r + 1]) {
                if (minDepth[l + 1][r] <= minDepth[l][r + 1]) {
                    travse(DOWN, l + 1, r);
                } else {
                    travse(RIGHT, l, r + 1);
                }
            }
        }
        return diffList;
    }

    public MyDiff(List<String> leftList, List<String> rightList) {
        for (int i = 0; i < leftList.size(); i++) {
            leftMap.put(i, leftList.get(i));
        }
        for (int i = 0; i < rightList.size(); i++) {
            rightMap.put(i, rightList.get(i));
        }
        height = leftList.size();
        width = rightList.size();
        MatchInitial();
        MaxPointANDMinDepthInitial();
    }

    public MyDiff(String[] leftList, String[] rightList) {
        for (int i = 0; i < leftList.length; i++) {
            leftMap.put(i, leftList[i]);
        }
        for (int i = 0; i < rightList.length; i++) {
            rightMap.put(i, rightList[i]);
        }
        height = leftList.length;
        width = rightList.length;
        MatchInitial();
        MaxPointANDMinDepthInitial();
    }

    public void PrintMinDepth() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(StringUtils.leftPad(String.valueOf(minDepth[i][j]), 3) + " ");
            }
            System.out.println();
        }
        System.out.println("height: " + height + ", width:" + width);
    }

    public void PrintMaxPoint() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(StringUtils.leftPad(String.valueOf(maxPoint[i][j]), 3) + " ");
            }
            System.out.println();
        }
        System.out.println("height: " + height + ", width:" + width);
    }

    public void PrintMatch() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(StringUtils.leftPad(String.valueOf(match[i][j]), 1) + " ");
            }
            System.out.println();
        }
        System.out.println("height: " + height + ", width:" + width);
    }

    private void MaxPointANDMinDepthInitial() {
        //初始化：最大匹配点数矩阵 & 最小路径矩阵
        maxPoint = new int[height + 1][width + 1];
        minDepth = new int[height + 1][width + 1];
        for (int i = height - 1; i >= 0; i--) {
            for (int j = width - 1; j >= 0; j--) {
                maxPoint[i][j] = max(maxPoint[i + 1][j], match[i][j] + maxPoint[i + 1][j + 1],
                        maxPoint[i][j + 1]);
                if (match[i][j] > 0) {
                    minDepth[i][j] = 1 + minDepth[i + 1][j + 1];
                } else {
                    if (maxPoint[i][j + 1] >= maxPoint[i + 1][j]) {
                        minDepth[i][j] = minDepth[i][j + 1];
                    } else {
                        minDepth[i][j] = 1 + minDepth[i + 1][j];
                    }
                }
            }
        }
    }

    private void MatchInitial() {
        //初始化：匹配点矩阵
        match = new int[height + 1][width + 1];
        for (int i = 0; i < height + 1; i++) {
            for (int j = 0; j < width + 1; j++) {
                if (i == height || j == width) {
                    match[i][j] = 0;
                } else {
                    if (leftMap.get(i).equals(rightMap.get(j))) {
                        match[i][j] = 1;
                    } else {
                        match[i][j] = 0;
                    }
                }
            }
        }
    }

    private int max(int a, int b) {
        return a >= b ? a : b;
    }

    private int max(int a, int b, int c) {
        return max(a, max(b, c));
    }
}
