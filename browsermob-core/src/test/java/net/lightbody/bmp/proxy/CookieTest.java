package net.lightbody.bmp.proxy;

import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarCookie;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.proxy.test.util.LocalServerTest;
import net.lightbody.bmp.proxy.util.IOUtils;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

public class CookieTest extends LocalServerTest {
    @Test
    public void testNoDoubleCookies() throws IOException {
        proxy.setCaptureContent(true);
        proxy.newHar("Test");

        // set the cookie on the server side
        IOUtils.toStringAndClose(client.execute(new HttpGet(getLocalServerHostnameAndPort() + "/cookie")).getEntity().getContent());

        String body = IOUtils.toStringAndClose(client.execute(new HttpGet(getLocalServerHostnameAndPort() + "/echo")).getEntity().getContent());
        int first = body.indexOf("foo=bar");
        int last = body.lastIndexOf("foo=bar");
        Assert.assertTrue("foo=bar cookie not found", first != -1);
        Assert.assertEquals("Multiple foo=bar cookies found", first, last);
    }

    @Test
    @Ignore // not sure how this test ever worked, since the code does literally nothing with response cookies. it should but it doesn't at the moment :)
    public void testCookiesAreCapturedWhenSet() throws IOException {
        proxy.setCaptureContent(true);
        proxy.newHar("Test");

        // set the cookie on the server side
        IOUtils.toStringAndClose(client.execute(new HttpGet(getLocalServerHostnameAndPort() + "/cookie")).getEntity().getContent());

        Har har = proxy.getHar();
        HarEntry entry = har.getLog().getEntries().get(0);
        HarCookie cookie = entry.getResponse().getCookies().get(0);
        Assert.assertEquals("foo", cookie.getName());
        Assert.assertEquals("bar", cookie.getValue());
    }

    @Test
    public void testCookiesAreCapturedWhenRequested() throws IOException {
        proxy.setCaptureContent(true);
        proxy.newHar("Test");

        BasicClientCookie cookie = new BasicClientCookie("foo", "bar");
        cookie.setDomain("127.0.0.1");
        cookie.setPath("/");
        cookieStore.addCookie(cookie);

        // set the cookie on the server side
        String body = IOUtils.toStringAndClose(client.execute(new HttpGet(getLocalServerHostnameAndPort() + "/echo")).getEntity().getContent());

        Har har = proxy.getHar();
        HarEntry entry = har.getLog().getEntries().get(0);
        HarCookie harCookie = entry.getRequest().getCookies().get(0);
        Assert.assertEquals("foo", harCookie.getName());
        Assert.assertEquals("bar", harCookie.getValue());
    }

}
