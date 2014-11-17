package com.company.ibank.controllers.configurators;

import java.util.ResourceBundle;

public class ConfigurationManager {
    public static final String TRANSFERS_VIEW_PAGE = "transfers_view";
    public static final String LOGIN_URL = "login_page";
    private ResourceBundle resourceBundle;
    private static final String BUNDLE_NAME = "config";

    public static ConfigurationManager getInstance() {
        ConfigurationManager instance = ConfigurationManagerHolder.INSTANCE;
        instance.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
        return instance;
    }

    public static class ConfigurationManagerHolder {
        public static final ConfigurationManager INSTANCE = new ConfigurationManager();
    }

    public String getProperty(String key) {
        return (String) resourceBundle.getObject(key);
    }
}
