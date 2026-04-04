package com.elvira.pages;

import com.elvira.core.context.ContextManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class BasePage {

    protected final Page page;

    public BasePage() {
        this.page = ContextManager.get().getPage(); }

    // 🔹 Page state waits
    protected void waitForPageLoad() {
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
    }

    protected void waitForNetworkIdle() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    protected void waitForUrl(String urlPart) {
        page.waitForURL(url -> url.contains(urlPart));
    }

    // 🔹 Element actions (safe wrappers)
    protected void click(Locator locator) {
        locator.waitFor();
        locator.click();
    }

    protected void type(Locator locator, String text) {
        locator.waitFor();
        locator.fill(text);
    }

    protected void waitVisible(Locator locator) {
        locator.waitFor();
    }

    protected boolean isVisible(Locator locator) {
        return locator.isVisible();
    }
}