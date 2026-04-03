package com.elvira.pages;

import com.elvira.core.config.ConfigReader;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import io.qameta.allure.Step;

public class EskizRezumePage extends BasePage {

    @Step("Open main page")
    public EskizRezumePage open() {
        page.navigate(ConfigReader.get("baseUrl"));
        return this;
    }

    @Step("Open 'Сайты'")
    public EskizRezumePage openSites() {
    page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Сайты")).click();
        return this;
    }

    @Step("Open 'Экспресс сайты'")
    public EskizRezumePage openExpressSites() {
    page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("ЭКСПРЕСС САЙТЫ").setExact(true)).click();
        return this;
    }

    @Step("Open order page")
    public EskizRezumePage openOrderPage() {
    page.getByRole(AriaRole.TAB, new Page.GetByRoleOptions().setName("Пять причин заказать себе сайт")).click();
        return this;
    }

    @Step("Open career section")
    public EskizRezumePage openCareer() {
    page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Карьера")).click();
        return this;
    }

    @Step("Attach resume")
    public EskizRezumePage attachResume() {
    page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Прикрепить резюме")).click();
        return this;
    }

    @Step("Submit resume")
    public EskizRezumePage submitResume() {
assertThat(
    page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Отправить резюме"))).isVisible();
        return this;
    }

        // 🔥 Locator для assertion (важно)
    public Locator submitResumeButton() {
        return page.getByText("Отправить резюме");
    }

    // 🔥 ещё один пример (если появится заголовок)
    public Locator resumeFormTitle() {
        return page.getByText("Отправить резюме");
    }

}