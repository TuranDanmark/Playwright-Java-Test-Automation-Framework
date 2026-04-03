package com.elvira.pages;

import com.elvira.core.context.ContextManager;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class BasePage {

    protected Page page;

    public BasePage() {
        this.page = ContextManager.get().getPage();
    }

    protected void waitForPageLoad() {
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
    }

    protected void waitForNetworkIdle() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }
}