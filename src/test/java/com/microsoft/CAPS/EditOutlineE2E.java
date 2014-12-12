package com.microsoft.CAPS;

import com.microsoft.CAPS.conf.TestProperties;
import com.microsoft.CAPS.util.WebUICommonTestHelper;
import net.lightbody.bmp.proxy.DummyServerTest;
import net.lightbody.bmp.proxy.ProxyServer;
import org.junit.Test;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 *
 * Created by Yang ZHANG on 12/11/2014.
 */
public class EditOutlineE2E extends DummyServerTest {
//    @Test
    public void EditOutline() {
        ProxyServer server = new ProxyServer(9090);
        server.start();
        WebDriver driver;

        Proxy proxy = server.seleniumProxy();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized", "--test-type");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability("chromeOptions", options);
        capabilities.setCapability(CapabilityType.PROXY, proxy);

        System.setProperty("webdriver.chrome.driver", "D:/t-yaz/Source/Repos/Test/Experience/E2ETest/packages/" +
                "Selenium.WebDriver.ChromeDriver.2.10.0.0/content/chromedriver.exe");

        driver = new ChromeDriver(capabilities);
        WebUICommonTestHelper testHelper = new WebUICommonTestHelper(TestProperties.DefaultProjectURL, driver);
        testHelper.login();

    }

    public static void main(String[] args) {
        EditOutlineE2E editOutlineE2E = new EditOutlineE2E();
        editOutlineE2E.EditOutline();
    }
}

