package com.elvira.utils;

import com.elvira.core.lifecyle.TestLifecycleManager;
import com.elvira.core.allure.AllureAttachmentService;
import com.microsoft.playwright.Page;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

public class TestListener implements TestWatcher {

@Override
public void testFailed(ExtensionContext context, Throwable cause) {

    Page page = safeGetPage();
    String testName = context.getDisplayName();

    tryAttach(() -> AllureAttachmentService.attachScreenshot(page));
    tryAttach(() -> AllureAttachmentService.attachTrace(page, testName));
    tryAttach(() -> AllureAttachmentService.attachVideo(page));
    tryAttach(() -> AllureAttachmentService.attachPageUrl(page));
    tryAttach(() -> AllureAttachmentService.attachError(cause));
    tryAttach(AllureAttachmentService::attachConsoleLogs);
    tryAttach(AllureAttachmentService::attachNetworkLogs);
}

private Page safeGetPage() {
    try {
        return TestLifecycleManager.getPage();
    } catch (Exception e) {
        return null;
    }
}

private void tryAttach(Runnable action) {
    try {
        action.run();
    } catch (Exception ignored) {
        // 💡 никогда не ломаем тест из-за аттачей
    }
}
}