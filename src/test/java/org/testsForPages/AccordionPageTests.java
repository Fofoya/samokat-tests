package org.testsForPages;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Assert;

import org.sitePages.MainPage;
import org.sitePages.AccordionPage;

public class AccordionPageTests {
    private WebDriver driver;

    // Массив вопросов
    private final String[] questions = {
            "Сколько это стоит? И как оплатить?",
            "Хочу сразу несколько самокатов! Так можно?",
            "Как рассчитывается время аренды?",
            "Можно ли заказать самокат прямо на сегодня?",
            "Можно ли продлить заказ или вернуть самокат раньше?",
            "Вы привозите зарядку вместе с самокатом?",
            "Можно ли отменить заказ?",
            "Я жизу за МКАДом, привезёте?"
    };

    // Массив ответов
    private final String[] answers = {
            "Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
            "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
            "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
            "Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
            "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
            "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
            "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
            "Да, обязательно. Всем самокатов! И Москве, и Московской области."
    };

    // Для удобства индексы вопросов и ответов
    private final boolean[] isVisible = {true,true,true,true,true,true,true,true};

    @Before
    public void newDriver() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        MainPage objMainPage = new MainPage(driver);
        objMainPage.waitForLoadElement();
        objMainPage.checkAndClickCookieAgreeButton();
        objMainPage.scrollToBottom();
    }

    @Test
    public void checkQuestionsAndAnswersTextsTest() {
        AccordionPage objAccordionPage = new AccordionPage(driver);

        for (int i = 0; i < questions.length; i++) {
            objAccordionPage.clickQuestionByIndex(i);
            Assert.assertEquals("Вопрос в списке отличается от ожидаемого", questions[i], objAccordionPage.getQuestionTextByIndex(i));
            Assert.assertEquals("Ответ в списке отличается от ожидаемого", answers[i], objAccordionPage.getAnswerTextByIndex(i));
        }
    }

    @Test
    public void clickOnAccordionQuestionTextAppearsTest() {
        AccordionPage objAccordionPage = new AccordionPage(driver);

        for (int i = 0; i < questions.length; i++) {
            objAccordionPage.clickQuestionByIndex(i);
            Assert.assertEquals("Видимость ответа не соответствует ожиданиям для индекса " + i, isVisible[i], objAccordionPage.getAnswerByIndex(i).isDisplayed());
        }
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
