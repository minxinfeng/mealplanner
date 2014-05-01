package com.threeone.mealplanner.common;

import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class CommonPropertyPlaceholderConfigurer  extends
	PropertyPlaceholderConfigurer{

	public CommonPropertyPlaceholderConfigurer() {
		super();
	}

	@Override
	protected String resolvePlaceholder(String placeholder, Properties props) {
		String value = PreferenceService.getCommonConfiguration()
				.getString(placeholder);
		if (value == null) {
			return super.resolvePlaceholder(placeholder, props);
		} else
			return value;
	}
}
