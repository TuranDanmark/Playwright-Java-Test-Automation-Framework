package com.elvira.core.context;

public class ContextManager {

    private static final ThreadLocal<TestContext> context = new ThreadLocal<>();

    public static void set(TestContext ctx) {
        if (context.get() != null) {
            throw new IllegalStateException("TestContext is already set for this thread");
        }
        context.set(ctx);
    }

    public static TestContext get() {
        TestContext ctx = context.get();
        if (ctx == null) {
            throw new IllegalStateException("TestContext is not initialized. Did you forget to call init()?");
        }
        return ctx;
    }

    public static void unload() {
        context.remove();
    }
}