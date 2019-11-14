package com.inori.skywalking.springbootwar.util;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class JavaBrowser {

    private static JavaBrowser javaBrowser;

    private static WebClient wc;

    static {
        wc = new WebClient(BrowserVersion.CHROME);
    }

    public static JavaBrowser getInstance() {
        if (javaBrowser == null) {
            javaBrowser = new JavaBrowser();
        }

        return javaBrowser;
    }

    public void visitWebsite(String url) throws IOException {
        log.info("开始访问网页，版本号{}", wc.getBrowserVersion());
        HtmlPage page = wc.getPage(url);
        log.info("页面内容 -> {}", page.toString());
    }
}
