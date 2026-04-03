package com.elvira.core.artifacts;

import java.util.ArrayList;
import java.util.List;

public class TestArtifacts {

    private static final ThreadLocal<List<String>> consoleLogs =
            ThreadLocal.withInitial(ArrayList::new);

    private static final ThreadLocal<List<String>> networkLogs =
            ThreadLocal.withInitial(ArrayList::new);

    private static final ThreadLocal<List<String>> errors =
            ThreadLocal.withInitial(ArrayList::new);

    // 🧾 Console logs
    public static void addConsoleLog(String log) {
        consoleLogs.get().add(log);
    }

    public static List<String> getConsoleLogs() {
        return consoleLogs.get();
    }

    // 📡 Network logs
    public static void addNetworkLog(String log) {
        networkLogs.get().add(log);
    }

    public static List<String> getNetworkLogs() {
        return networkLogs.get();
    }

    // ❗ JS / runtime errors (опционально)
    public static void addError(String error) {
        errors.get().add(error);
    }

    public static List<String> getErrors() {
        return errors.get();
    }

    // 🧹 очистка после теста (ОЧЕНЬ важно для parallel)
    public static void clear() {
        consoleLogs.remove();
        networkLogs.remove();
        errors.remove();
    }
}