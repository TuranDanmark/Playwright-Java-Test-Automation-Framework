package com.elvira.pages;

import com.elvira.core.allure.AllureAttachmentService;
import com.elvira.core.context.ContextManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;

public class BasePage {

    protected final Page page;

    public BasePage() {
        this.page = ContextManager.get().getPage();
    }

    // 🔹 Page state waits
    protected void waitForPageLoad() {
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
        log("[WAIT] Page DOM content loaded");
    }

    protected void waitForNetworkIdle() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
        log("[WAIT] Network idle");
    }

    protected void waitForUrl(String urlPart) {
        page.waitForURL(url -> url.contains(urlPart));
        log("[WAIT] URL contains: " + urlPart);
    }

    // 🔹 Element actions (safe wrappers)
    protected void click(Locator locator, String description) {
        try {
            locator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            locator.click();
            log("[ACTION] Clicked: " + description);
        } catch (Exception e) {
            logError("[CLICK FAILED] " + description, e);
            throw e;
        }
    }

    protected void type(Locator locator, String text, String description) {
        try {
            locator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            locator.fill(text);
            log("[ACTION] Typed '" + text + "' into: " + description);
        } catch (Exception e) {
            logError("[TYPE FAILED] " + description, e);
            throw e;
        }
    }

    public void waitVisible(Locator locator, String description, int timeoutMs) {
        try {
            locator.waitFor(new Locator.WaitForOptions().setTimeout(timeoutMs).setState(WaitForSelectorState.VISIBLE));
            log("[WAIT] Visible: " + description);
        } catch (Exception e) {
            logError("[WAIT FAILED] " + description, e);
            throw e;
        }
    }

    protected boolean isVisible(Locator locator) {
        try {
            return locator.isVisible();
        } catch (Exception e) {
            logError("[VISIBILITY CHECK FAILED]", e);
            return false;
        }
    }

    // 🔧 Helpers
    public void log(String message) {
        System.out.println("[PAGE] " + message);
    }

    private void logError(String message, Exception e) {
        System.err.println("[PAGE ERROR] " + message + " | " + e.getMessage());
        // Авто-скриншот при ошибке для Allure
        AllureAttachmentService.attachScreenshot(page);
    }
}