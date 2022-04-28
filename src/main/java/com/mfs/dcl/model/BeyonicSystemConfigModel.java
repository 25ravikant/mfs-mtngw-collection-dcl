package com.mfs.dcl.model;

/*
 * BeyonicSystemConfigModel Class for configuration
 *
 * @since 28-04-2022
 */

public class BeyonicSystemConfigModel {

	private int configId;

	private String configKey;

	String configValue;

	public int getConfigId() {
		return configId;
	}

	public void setConfigId(int configId) {
		this.configId = configId;
	}

	public String getConfigKey() {
		return configKey;
	}

	public void setConfigKey(String configKey) {
		this.configKey = configKey;
	}

	public String getConfigValue() {
		return configValue;
	}

	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}

	//toString Method
	@Override
	public String toString() {
		return "BeyonicSystemConfigModel [configId=" + configId + ", configKey=" + configKey + ", configValue="
				+ configValue + "]";
	}

	

}
