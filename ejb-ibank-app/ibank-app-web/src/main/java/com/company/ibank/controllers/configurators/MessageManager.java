package com.company.ibank.controllers.configurators;

import java.util.ResourceBundle;

public class MessageManager {
    public static final String LOGIN_ERROR_MESSAGE = "LOGIN_ERROR_MESSAGE";
    private ResourceBundle resourceBundle;
    private static final String BUNDLE_NAME = "messages";


    public static MessageManager getInstance() {
        MessageManager instance = MessageManagerHolder.INSTANCE;
        instance.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME + "_en");
        return instance;
    }

    public static class MessageManagerHolder {
        public static final MessageManager INSTANCE = new MessageManager();
    }

    public static MessageManager getInstance(String language) {
        if (language == null || language.equals("")) {
            return getInstance();
        } else {
            MessageManager instance = MessageManagerHolder.INSTANCE;
            instance.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME + "_" + language);
            return instance;
        }
    }

    public String getProperty(String key) {
        return (String) resourceBundle.getObject(key);
    }
}
