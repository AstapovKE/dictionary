package com.kastapov.server.service;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.lang3.StringUtils;

/**
 * User: kastapov
 * Date: 23.08.2015
 */
public class PropertiesHolderService {

    private static final Object SYNC_ROOT = new Object();

    private static final String ENCODING = "UTF-8";
    private static final String APP_PROPERTIES_FILE_NAME = "application.properties";

    private static volatile PropertiesHolderService propertiesHolderService;
    private PropertiesConfiguration configuration;

    private PropertiesHolderService(String fileName) throws Exception {
        configuration = new PropertiesConfiguration();
        configuration.setEncoding(ENCODING);
        configuration.setReloadingStrategy(new FileChangedReloadingStrategy());
        configuration.setThrowExceptionOnMissing(true);
        configuration.load(fileName);
    }

    public static PropertiesHolderService getProperties() {
        if (propertiesHolderService == null) {
            synchronized (SYNC_ROOT) {
                if (propertiesHolderService == null) {
                    try {
                        propertiesHolderService = new PropertiesHolderService(APP_PROPERTIES_FILE_NAME);
                    } catch (Exception e) {
                        throw new ExceptionInInitializerError("Unable to load properties files.");
                    }
                }
            }
        }
        return propertiesHolderService;
    }

    public String getString(String key) {
        String result = null;
        if (configuration != null) {
            result = configuration.getString(key, StringUtils.EMPTY);
        } else {
            throw new NullPointerException("Properties not loaded");
        }
        return result;
    }

    public Integer getInteger(String key) {
        Integer result = null;
        if (configuration != null) {
            result = configuration.getInteger(key, null);
        } else {
            throw new NullPointerException("Properties not loaded");
        }
        return result;
    }


}
