package com.elvira.core.context;

public class ContextManager {

    private static final ThreadLocal<TestContext> context = new ThreadLocal<>();

    public static void set(TestContext ctx) {
        context.set(ctx);
    }

    public static TestContext get() {
        return context.get();
    }

    public static void unload() {
        context.remove();
    }
}