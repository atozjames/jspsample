package com.mychekup.jspboard;

import org.apache.log4j.Logger;

public class LogTest {

	static Logger logger = Logger.getLogger(LogTest.class);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//DEBUG < INFO < WARN < ERROR < FATAL

		logger.fatal("log4j:logger.fatal()");

		logger.error("log4j:logger.error()");

		logger.warn("log4j:logger.warn()");

		logger.info("log4j:logger.info()");

		logger.debug("log4j:logger.debug()");
		
		logger.info("Hello World!!");

	}

}
