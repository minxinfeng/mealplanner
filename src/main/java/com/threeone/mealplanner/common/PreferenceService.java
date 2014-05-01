package com.threeone.mealplanner.common;

import java.util.Iterator;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class PreferenceService {
	private static Log log = LogFactory.getLog(PreferenceService.class);
	private static CompositeConfiguration configuration;


	public static String getConfigBasePath(){
		String configPath = CommonPropertyPlaceholderConfigurer.class.getClassLoader()
				.getResource("").getPath() + "config/";
		return configPath;
	}
	
	public static synchronized void init() {
		if (configuration == null){
			configuration = new CompositeConfiguration();
			
			PropertiesConfiguration propertiesConfiguration;
			
			String proppath = getConfigBasePath() + "common/common.properties";
			try {
				propertiesConfiguration = new PropertiesConfiguration(proppath);
				for (Iterator<String> iterator = propertiesConfiguration.getKeys(); iterator.hasNext();) {
					String key = iterator.next();
					log.debug("Init property from common.properties : "
							+ key
							+ " = "
							+ propertiesConfiguration.getProperty(key));
				}

				configuration.addConfiguration(propertiesConfiguration);
			} catch (ConfigurationException e) {
				log.error(e.getMessage(), e);
			}
			
			proppath = getConfigBasePath() + SystemInfoParser.getHostName()
					+ ".properties";
			try {
				propertiesConfiguration = new PropertiesConfiguration(proppath);
				for (Iterator<String> iterator = propertiesConfiguration.getKeys(); iterator.hasNext();) {
					String key = iterator.next();
					log.debug("Init property from: " + SystemInfoParser.getHostName()
							+ ".properties : " +  key
							+ " = "
							+ propertiesConfiguration.getProperty(key));
				}
				configuration.addConfiguration(propertiesConfiguration);
			} catch (ConfigurationException e) {
				log.error(e.getMessage(), e);
			}
			
		}
	}

	/**
	 * hardcode...
	 * 
	 * @return
	 */
	public static Configuration getCommonConfiguration() {
		if (configuration != null)
			return configuration;
		init();
		return getCommonConfiguration();
	}

	public Configuration getConfiguration() {
		return getCommonConfiguration();
	}
}