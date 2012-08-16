/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-6-3
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
package com.liubida.ThinkingInJAVA.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

/**
 * @author liubida
 */
public class BufferedInputFileExercise7 {
	private static String readC(String filename) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(filename));
		int c = 0;
		Stack<Character> stack = new Stack<Character>();
		while (-1 != (c = in.read())) {
			stack.push((char) c);
		}
		StringBuilder sb = new StringBuilder();
		while (!stack.empty()) {
			sb.append(stack.pop());
		}
		return sb.toString();
	}

	private static String read(String filename) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(filename));
		String s;
		Stack<String> stack = new Stack<String>();
		while (null != (s = in.readLine())) {
			stack.push(s);
		}
		StringBuilder sb = new StringBuilder();
		while (!stack.empty()) {
			sb.append(stack.pop());
			sb.append('\n');
		}
		return sb.toString();
	}

	public static void main(String[] args) throws IOException {
		System.out.println(read("./src/io/DirList3.java"));
		System.out.println("----------------------------");
		System.out.println(readC("./src/io/BufferedInputFileExercise7.java"));
	}
}
