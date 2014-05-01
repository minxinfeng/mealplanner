package com.threeone.mealplanner.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SystemInfoParser {
	private static final Log log = LogFactory.getLog(SystemInfoParser.class);

	public static String getHostName() {
		String all = null;

		BufferedReader br = null;
		String os = System.getProperty("os.name");
		if (os != null && !os.startsWith("Windows")) {
			try {
				Process p = Runtime.getRuntime().exec("hostname");
				br = new BufferedReader(new InputStreamReader(p.getInputStream()));
				all = br.readLine();
			} catch (IOException e) {
				log.error("Error in SystemInfoParser", e);
			} finally {

				try {
					br.close();
				} catch (IOException e) {
					log.error("Error in SystemInfoParser", e);
				}
			}
		} else if (os != null) {
			all = System.getenv("COMPUTERNAME");
		}

		if (all == null)
			throw new RuntimeException("Host Name is Null");
		return all;
	}

}