package com.microsoft.CAPS;

import com.microsoft.CAPS.conf.TestProperties;
import net.lightbody.bmp.proxy.DummyServerTest;
import net.lightbody.bmp.proxy.ProxyServer;
import org.junit.Test;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 *
 * Created by Yang ZHANG on 12/11/2014.
 */
public class EditOutlineE2E extends DummyServerTest {
    @Test
    public void EditOutline() {
        ProxyServer server = new ProxyServer(0);
        server.start();
        WebDriver driver;

        Proxy proxy = server.seleniumProxy();
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(CapabilityType.PROXY, proxy);

        driver = new FirefoxDriver(capabilities);
        driver.navigate().to(TestProperties.DefaultProjectURL);

    }
}

