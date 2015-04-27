package com.chengjf.zeal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Test {

	private static Log log = LogFactory.getLog(Test.class);

	public static void main(String[] args) {
		log.debug("Start.");
		System.out.println("Hello, world!");
		log.debug("End.");
	}
}
