package com.elvira.core.browser;

public enum BrowserType {
    CHROMIUM,
    FIREFOX,
    WEBKIT;

public static BrowserType fromOrDefault(String value, BrowserType defaultType) {
    try {
        return BrowserType.valueOf(value.toUpperCase());
    } catch (Exception e) {
        return defaultType;
    }
}
}