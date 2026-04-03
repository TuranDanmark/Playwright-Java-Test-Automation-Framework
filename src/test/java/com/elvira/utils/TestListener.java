package com.elvira.utils;

import com.elvira.core.lifecyle.TestLifecycleManager;
import com.elvira.core.allure.AllureAttachmentService;
import com.microsoft.playwright.Page;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

public class TestListener implements TestWatcher {

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {

        Page page = TestLifecycleManager.getPage();
        if (page == null) return;

        String testName = context.getDisplayName();

        // 📸 Screenshot
        AllureAttachmentService.attachScreenshot(page);

        // 📦 Trace
        AllureAttachmentService.attachTrace(page, testName);

        // 🎥 Video
        AllureAttachmentService.attachVideo(page);

        // 🌐 URL
        AllureAttachmentService.attachPageUrl(page);

        // ❗ Error
        AllureAttachmentService.attachError(cause);

        AllureAttachmentService.attachConsoleLogs();

        AllureAttachmentService.attachNetworkLogs();

    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        // 👉 опционально: можно останавливать trace без сохранения
        // или вообще ничего не делать
    }


}