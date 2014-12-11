package com.microsoft.CAPS.conf;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * Created by Yang Zhang on 12/11/2014.
 */
public class TestProperties {
    public static String DefaultProjectURL;
    public static String Account;
    public static String Password;

    static {
        Properties properties = new Properties();
        InputStream in = TestProperties.class.getResourceAsStream("test.properties");
        try {
            properties.load(in);
            DefaultProjectURL = properties.getProperty("CapsE2ETestDefaultProjectURL");
            Account = properties.getProperty("CapsTestAccount");
            Password = properties.getProperty("CapsTestAccountPassword");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TestProperties() {}
}
