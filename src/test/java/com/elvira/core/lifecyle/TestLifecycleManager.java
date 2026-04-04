package com.elvira.core.lifecyle;

import java.nio.file.Paths;

import com.elvira.core.artifacts.TestArtifacts;
import com.elvira.core.browser.BrowserFactory;
import com.elvira.core.config.ConfigReader;
import com.elvira.core.context.ContextManager;
import com.elvira.core.context.TestContext;
import com.elvira.core.tracing.TracingManager;
import com.elvira.utils.PageEventListener;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class TestLifecycleManager {

    public static void init() {

        TestContext ctx = new TestContext();

        Playwright playwright = Playwright.create();
        ctx.setPlaywright(playwright);

        Browser browser = BrowserFactory.createBrowser(playwright);
        ctx.setBrowser(browser);

        BrowserContext context = browser.newContext(
                new Browser.NewContextOptions()
                        .setViewportSize(1280, 800)
                        .setRecordVideoDir(Paths.get("target/videos"))
        );
        ctx.setContext(context);

        TracingManager.start(context);

        Page page = context.newPage();
        ctx.setPage(page);

        PageEventListener.attach(page);

        page.navigate(ConfigReader.get("baseUrl"));
        page.setDefaultTimeout(ConfigReader.getInt("timeout"));

        ContextManager.set(ctx);
    }

    public static void cleanup() {

        TestContext ctx = ContextManager.get();

        TracingManager.stop(ctx.getContext(),"");

        if (ctx.getContext() != null) ctx.getContext().close();
        if (ctx.getBrowser() != null) ctx.getBrowser().close();
        if (ctx.getPlaywright() != null) ctx.getPlaywright().close();

        TestArtifacts.clear();

        ContextManager.unload();
    }

    public static Page getPage() {
        if (ContextManager.get() == null) {
        return null;
    }
        return ContextManager.get().getPage();
}
}
