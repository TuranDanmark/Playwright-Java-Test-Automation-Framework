package com.elvira.core.base;

import com.elvira.core.allure.AllureEnvironmentWriter;
import com.elvira.core.lifecyle.TestLifecycleManager;
import com.elvira.utils.TestListener;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(TestListener.class)
public abstract class BaseTest {

    @BeforeAll
    void globalSetup() {
        AllureEnvironmentWriter.write();
        log("Global setup completed");
    }

    @BeforeEach
    void setUp(TestInfo testInfo) {
        log("Starting test: " + testInfo.getDisplayName());

        try {
            TestLifecycleManager.init();
        } catch (Exception e) {
            log("FAILED to initialize test lifecycle");
            throw e;
        }
    }

    @AfterEach
    void tearDown(TestInfo testInfo) {
        log("Finishing test: " + testInfo.getDisplayName());

        try {
            TestLifecycleManager.cleanup();
        } catch (Exception e) {
            log("FAILED during cleanup");
            throw e;
        }
    }

    protected Page page() {
        Page page = TestLifecycleManager.getPage();

        if (page == null) {
            throw new IllegalStateException("Page is not initialized. Did you forget to call init()?");
        }

        return page;
    }

    // 🔧 простой встроенный логгер (можно заменить на Log4j)
    private void log(String message) {
        System.out.println("[BaseTest] " + message);
    }
}