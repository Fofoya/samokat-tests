package org.sitePages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class AccordionPage {
    private WebDriver driver;

    public AccordionPage(WebDriver driver) {
        this.driver = driver;
    }

    // Базовые шаблоны локаторов
    private final String questionLocatorTemplate = "accordion__heading-%d";
    private final String answerLocatorTemplate = "accordion__panel-%d";
    // все вопросы из блока вопросов
    private By allAccordionQuestions = By.xpath("//div[starts-with(@id,'accordion__heading-')]");
    // все ответы из блока вопросов
    private By allAccordionAnswers = By.xpath("//div[starts-with(@id,'accordion__panel-')]");

    // Метод для получения всех вопросов в блоке вопросов
    public List<WebElement> getAllQuestions() {
        return driver.findElements(allAccordionQuestions);
    }
    // Метод для получения всех ответов в блоке вопросов
    public List<WebElement> getAllAnswers() {
        return driver.findElements(allAccordionAnswers);
    }
    // Получение конкретного вопроса по индексу
    public WebElement getQuestionByIndex(int index) {
        By questionLocator = By.id(String.format(questionLocatorTemplate, index));
        return driver.findElement(questionLocator);
    }
    // Получение конкретного ответа по индексу
    public WebElement getAnswerByIndex(int index) {
        By answerLocator = By.id(String.format(answerLocatorTemplate, index));
        return driver.findElement(answerLocator);
    }
    // Клик по вопросу по индексу
    public void clickQuestionByIndex(int index) {
        getQuestionByIndex(index).click();
    }
    // Получение текста ответа по индексу
    public String getQuestionTextByIndex(int index) {
        return getQuestionByIndex(index).getText();
    }
    // Получение текста ответа по индексу
    public String getAnswerTextByIndex(int index) {
        return getAnswerByIndex(index).getText();
    }
}
