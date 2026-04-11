package com.elvira.core.tracing;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Tracing;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TracingManager {

    public static void start(BrowserContext context) {
        if (context == null) {
            log("Tracing start skipped: context is null");
            return;
        }

        try {
            context.tracing().start(
                    new Tracing.StartOptions()
                            .setScreenshots(true)
                            .setSnapshots(true)
                            .setSources(true)
            );
            log("Tracing started for context");
        } catch (Exception e) {
            log("Tracing start failed: " + e.getMessage());
        }
    }

    public static void stop(BrowserContext context, String testName) {
        if (context == null) {
            log("Tracing stop skipped: context is null");
            return;
        }

        try {
            // ✔ безопасное имя (без mutation в лямбдах и без .zip)
            String safeName = (testName == null || testName.isEmpty())
                    ? generateTraceName()
                    : testName;

            Path tracesDir = Paths.get("target/traces");
            Files.createDirectories(tracesDir);

            // ❗ ВАЖНО: НЕ добавляем .zip — Playwright сделает это сам
            Path path = tracesDir.resolve(safeName);

            // ✔ безопасный stop (если context уже закрыт — не падаем)
            try {
                context.tracing().stop(
                        new Tracing.StopOptions().setPath(path)
                );
                log("Tracing stopped and saved to " + path + ".zip");
            } catch (Exception e) {
                log("Tracing already stopped or context closed: " + e.getMessage());
            }

        } catch (Exception e) {
            log("Tracing stop failed: " + e.getMessage());
        }
    }

    public static String generateTraceName() {
        return "trace-" + System.currentTimeMillis();
    }

    private static void log(String message) {
        System.out.println("[TracingManager] " + message);
    }
}