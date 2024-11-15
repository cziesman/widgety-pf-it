package com.redhat.widget.helper;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "webdriver")
public class WebDriverConfigProperties {

    private String path;

    private String binary;

    private boolean headless;

    private int timeout;

    public String getPath() {

        return path;
    }

    public void setPath(String path) {

        this.path = path;
    }

    public String getBinary() {

        return binary;
    }

    public void setBinary(String binary) {

        this.binary = binary;
    }

    public boolean isHeadless() {

        return headless;
    }

    public void setHeadless(boolean headless) {

        this.headless = headless;
    }

    public int getTimeout() {

        return timeout;
    }

    public void setTimeout(int timeout) {

        this.timeout = timeout;
    }

}
