package com.elvira.pages;

import com.elvira.core.config.ConfigReader;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import io.qameta.allure.Step;

public class EskizRezumePage extends BasePage {

    private static final String BTN_SITES = "Сайты";
    private static final String LINK_EXPRESS = "ЭКСПРЕСС САЙТЫ";
    private static final String LINK_CAREER = "Карьера";
    private static final String LINK_ATTACH_RESUME = "Прикрепить резюме";

    @Step("Open main page")
    public EskizRezumePage open() {
        page.navigate(ConfigReader.get("baseUrl"));
        waitForPageLoad();
        log("[PAGE] Main page opened");
        return this;
    }

    @Step("Open 'Сайты'")
    public EskizRezumePage openSites() {
        click(page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(BTN_SITES)), "Open 'Сайты'");
        return this;
    }

    @Step("Open 'Экспресс сайты'")
    public EskizRezumePage openExpressSites() {
        click(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(LINK_EXPRESS).setExact(true)), "Open 'Экспресс сайты'");
        return this;
    }

    @Step("Open career section")
    public EskizRezumePage openCareer() {
        click(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(LINK_CAREER)), "Open 'Карьера'");
        return this;
    }

    @Step("Attach resume")
    public EskizRezumePage attachResume() {
        click(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(LINK_ATTACH_RESUME)), "Attach resume");
        return this;
    }

    // ✅ Locator для assertions
    public Locator resumeFormTitle() {
        return page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Отправить резюме"));
    }
}