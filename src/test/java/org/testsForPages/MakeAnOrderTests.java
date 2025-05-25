package org.testsForPages;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.matchers.JUnitMatchers;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.sitePages.OrderPage;
import org.sitePages.MainPage;

@RunWith(Parameterized.class)
public class MakeAnOrderTests {
    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String phone;
    private final int dateOffset;
    private final int dropdownIndex;
    private final int checkboxIndex;
    private final String commentText;
    private final String SuccessPageTextExpected;
    private WebDriver driver;

    public MakeAnOrderTests(String name,String surname,String address, String metro, String phone, int dateOffset, int dropdownIndex, int checkboxIndex, String commentText, String SuccessPageTextExpected) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.dateOffset = dateOffset;
        this.dropdownIndex = dropdownIndex;
        this.checkboxIndex = checkboxIndex;
        this.commentText = commentText;
        this.SuccessPageTextExpected = SuccessPageTextExpected;
    }

    @Parameterized.Parameters
    public static Object[][] getQuestionsAndAnswers() {
        return new Object[][] {
                {"Иван", "Иванов", "ул. Ленина, д.17, кв.60", "Академическая", "+79999999999", 1, 0, 0, "Йа креветко", "Заказ оформлен"},
                {"Никита", "Петрович", "блаблабла", "Маяковская", "89217775544", 3, 1, 1, "please call me 15 mins before arrival", "Заказ оформлен"},
        };
    }

    @Before
    public void newDriver() {
        driver = new FirefoxDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        MainPage objMainPage = new MainPage(driver);
        objMainPage.waitForLoadElement();
        objMainPage.clickFirstOrderButton();
    }

    @Test
    public void makeAnOrderSuccessTest() {
        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.checkOrderFormAppeared(); //проверяем что открылась форма оформления заказа
        objOrderPage.fillFirstStep(name, surname, address, metro, phone); //заполняем первый шаг
        objOrderPage.fillSecondStep(dateOffset, dropdownIndex, checkboxIndex, commentText); //заполняем второй шаг и оформляем заказ
        String successPageText = objOrderPage.getTextSuccessModalPage(); //проверяем что отобразилось на success page
        Assert.assertThat("Не удалось оформить заказ, некорректный success page",
                successPageText,
                JUnitMatchers.containsString(SuccessPageTextExpected));
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}