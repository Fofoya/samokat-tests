package org.sitePages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// Класс страницы авторизации
public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    //логотип "Самокат"
    private By samokatLogo = By.cssSelector("a.Header_LogoScooter__3lsAR");
    //верхняя кнопка "Заказать"
    private By firstOrderButton = By.xpath(".//button[@class='Button_Button__ra12g' and text()='Заказать']");
    //нижняя кнопка "Заказать"
    private By secondOrderButton = By.cssSelector("button.Button_Button__ra12g.Button_Middle__1CSJM");
    //кнопка соглашения с куками
    private By cookieAgreeButton = By.cssSelector("button.App_CookieButton__3cvqF.App_CookieButton__3cvqF");

    //метод для ожидания пока загрузится элемент
    public void waitForLoadElement(){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(samokatLogo));
    }
    //метод для клика на первую кнопку "Заказать"
    public void clickFirstOrderButton() {
        driver.findElement(firstOrderButton).click();
    }
    //метод для клика на вторую кнопку "Заказать"
    public void clickSecondOrderButton() {
        driver.findElement(secondOrderButton).click();
    }
    // Метод для прокрутки страницы в самый низ
    public void scrollToBottom() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
    // Метод проверки наличия кнопки соглашения с куками (клик, если присутствует на сайте)
    public void checkAndClickCookieAgreeButton() {
        if (driver.findElement(cookieAgreeButton).isDisplayed()) {
            driver.findElement(cookieAgreeButton).click();
        }
    }
}