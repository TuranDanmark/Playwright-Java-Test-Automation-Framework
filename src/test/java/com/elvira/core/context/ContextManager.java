package com.elvira.core.context;

public class ContextManager {

    private static final ThreadLocal<TestContext> context = new ThreadLocal<>();

    public static void set(TestContext ctx) {
        if (ctx == null) {
            throw new IllegalArgumentException("Cannot set null TestContext");
        }
        if (context.get() != null) {
            throw new IllegalStateException(
                    "TestContext is already set for this thread. " +
                    "Possible double init or parallel test conflict"
            );
        }
        context.set(ctx);
        log("TestContext set for thread: " + Thread.currentThread().getName());
    }

    /**
     * ❗ Строгий метод — используется в тестах
     */
    public static TestContext get() {
        TestContext ctx = context.get();
        if (ctx == null) {
            throw new IllegalStateException(
                    "TestContext is not initialized. Did you forget to call init()?"
            );
        }
        return ctx;
    }

    /**
     * ✅ Безопасный метод — используется в cleanup / hooks
     */
    public static TestContext getOrNull() {
        return context.get();
    }

    /**
     * (опционально, но очень удобно)
     */
    public static boolean isInitialized() {
        return context.get() != null;
    }

    public static void unload() {
        if (context.get() != null) {
            log("Unloading TestContext for thread: " + Thread.currentThread().getName());
            context.remove();
        }
    }

    private static void log(String message) {
        System.out.println("[ContextManager] " + message);
    }
}