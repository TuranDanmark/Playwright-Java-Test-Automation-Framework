package com.elvira.core.tracing;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Tracing;

import java.nio.file.Path;
import java.nio.file.Paths;

public class TracingManager {

    public static void start(BrowserContext context) {
        context.tracing().start(
                new Tracing.StartOptions()
                        .setScreenshots(true)
                        .setSnapshots(true)
                        .setSources(true)
        );
    }

    public static void stop(BrowserContext context, String testName) {
        try {
            Path path = Paths.get("target/traces/" + testName + ".zip");

            context.tracing().stop(
                    new Tracing.StopOptions().setPath(path)
            );

        } catch (Exception e) {
            System.err.println("Failed to stop tracing: " + e.getMessage());
        }
    }
}