package com.hillel.auto.config;

/**
 * Created by alpa on 5/14/20
 */
public class UnsupportedBrowserException extends AssertionError {

    public UnsupportedBrowserException(String browser) {
        super("Unsupported browser " +  browser + "!");
    }
}
