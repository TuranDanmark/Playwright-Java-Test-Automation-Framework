package com.elvira.utils;

import com.elvira.core.artifacts.TestArtifacts;
import com.microsoft.playwright.Page;

public class PageEventListener {

    public static void attach(Page page) {

        page.onConsoleMessage(msg ->
                TestArtifacts.addConsoleLog("[CONSOLE] " + msg.type() + " → " + msg.text()));

        page.onRequest(req ->
                TestArtifacts.addNetworkLog("[REQUEST] " + req.method() + " " + req.url()));

        page.onResponse(res ->
                TestArtifacts.addNetworkLog("[RESPONSE] " + res.status() + " " + res.url()));

        page.onPageError(error ->
                TestArtifacts.addError("[JS ERROR] " + error));
    }
}