# 🎭 Playwright Java Test Automation Framework

![Java](https://img.shields.io/badge/Java-17+-blue)
![Build](https://img.shields.io/badge/build-passing-brightgreen)
![Tests](https://img.shields.io/badge/tests-automated-success)
![Allure](https://img.shields.io/badge/reporting-Allure-orange)
![License](https://img.shields.io/badge/license-MIT-lightgrey)

A **production-ready test automation framework** built with **Playwright (Java)**, **JUnit 5**, and **Allure Reporting**.

Designed for:

* ⚡ Parallel execution
* 🔍 Deep observability (logs, traces, video)
* 🧱 Clean, scalable architecture
* 🚀 CI/CD integration

---

## 🚀 Key Features

* 🎭 **Playwright Java** for modern browser automation
* 🧪 **JUnit 5** with custom extensions
* 🔁 **Retry mechanism** for flaky tests
* 🧵 **Thread-safe parallel execution** (ThreadLocal आधारित architecture)
* 🧱 **Page Object Model (POM)**
* 📊 **Allure Reports with rich artifacts**:

  * 📸 Screenshots
  * 🎥 Video recordings
  * 📦 Trace files
  * 🌐 Network logs
  * 🖥️ Console logs
  * ❗ Error details & URL

---

## 🏗️ Architecture Overview

```bash
com.elvira
│
├── core
│   ├── base              # BaseTest (entry point)
│   ├── browser           # BrowserFactory, BrowserType
│   ├── config            # ConfigReader
│   ├── context           # TestContext, ContextManager
│   ├── lifecycle         # TestLifecycleManager (heart of framework)
│   ├── tracing           # TracingManager
│   ├── allure            # Attachments + environment
│   └── extension         # RetryExtension
│
├── pages                 # Page Objects (BasePage, etc.)
│
├── utils
│   ├── TestListener      # Allure integration
│   └── PageEventListener # Logs collector
│
└── artifacts             # TestArtifacts storage
```

---

## ⚙️ Configuration

### 📄 `config.properties`

```properties
browser=chromium
headless=false
baseUrl=https://example.com
timeout=30000
```

### 🔧 Override via CLI:

```bash
mvn test -Dbrowser=firefox -Dheadless=true
```

---

## ▶️ Running Tests

### Run all tests:

```bash
mvn clean test
```

### Run specific browser:

```bash
mvn clean test -Dbrowser=webkit
```

### Headless mode:

```bash
mvn clean test -Dheadless=true
```

---

## 📊 Allure Reporting

### Generate & open report:

```bash
allure serve target/allure-results
```

### 🔍 Report includes:

* 📸 Failure screenshots
* 🎥 Test videos
* 📦 Playwright traces (debug like DevTools)
* 🌐 Network activity
* 🖥️ Console logs
* ❗ Stack traces & errors

---

## 🔁 Retry Strategy

* Automatically retries failed tests
* Configurable retry count
* Implemented via `RetryExtension`

---

## 🧠 Design Principles

* **Single Source of Truth**

  * Page → `TestContext`
  * Config → `ConfigReader`
  * Attachments → `AllureAttachmentService`

* **Separation of Concerns**

  * Tests → logic
  * Pages → actions
  * Core → infrastructure

* **Thread Safety**

  * All contexts isolated via `ThreadLocal`

---

## 📌 Best Practices

✔ Keep assertions inside tests
✔ Keep Page Objects clean (no assertions)
✔ Avoid duplicated logic
✔ Use centralized lifecycle (`TestLifecycleManager`)
✔ Always attach artifacts on failure

---

## 🛠️ Tech Stack

* ☕ Java 17+
* 🎭 Playwright
* 🧪 JUnit 5
* 📊 Allure Report
* 📦 Maven

---

## 🚀 CI/CD Ready

This framework is designed to integrate easily with:

* GitHub Actions
* Jenkins
* GitLab CI

Supports:

* Parallel runs
* Headless execution
* Artifact collection

---

## 📈 Future Improvements

* API testing layer (REST/GraphQL)
* Test data management
* Dockerized execution
* Advanced reporting (metrics, flaky test tracking)
* Logging framework (SLF4J / Log4j)

---

## 👤 Author
TuranGulAmran — QA Automation Engineer

Designed as a **scalable, maintainable, and real-world automation framework** suitable for enterprise-level testing.

---

⭐ If you find this project useful — consider starring the repository!
