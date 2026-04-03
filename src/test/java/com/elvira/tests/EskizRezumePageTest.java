package com.elvira.tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.elvira.core.base.BaseTest;
import com.elvira.core.extension.RetryExtension;
import com.elvira.pages.EskizRezumePage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Epic("EskizRezumePage App")
@Feature("EskizRezume Management")
@ExtendWith(RetryExtension.class)
public class EskizRezumePageTest extends BaseTest {

@Tag("regression")
@Test
@Story("User can submit Resume")
@Description("Verify that user can asserted Resume visible")
@Severity(SeverityLevel.CRITICAL)
void resumeTest() {

EskizRezumePage eskizPage = new EskizRezumePage()
            .open()
            .openSites()
            .openExpressSites()
            .openOrderPage()
            .openCareer()
            .attachResume()
            .submitResume();
        
        // ✅ Assertion (Playwright style)
        assertThat(eskizPage.submitResumeButton()).isVisible();

        // ✅ Альтернатива (если нужно проверить текст)
        assertThat(eskizPage.resumeFormTitle()).containsText("Отправить резюме");

}
}