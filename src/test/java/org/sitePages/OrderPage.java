package org.sitePages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class OrderPage {
    private WebDriver driver;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }
    //форма оформления заказа
    private By orderForm = By.className("Order_Form__17u6u");
    //поле ввода имени
    private By name = By.xpath(".//input[@class='Input_Input__1iN_Z Input_Responsible__1jDKN' and contains(@placeholder, 'Имя')]");
    //поле ввода фамилии
    private By surname = By.xpath(".//input[@class='Input_Input__1iN_Z Input_Responsible__1jDKN' and contains(@placeholder, 'Фамилия')]");
    //поле ввода адреса
    private By address = By.xpath(".//input[@class='Input_Input__1iN_Z Input_Responsible__1jDKN' and contains(@placeholder, 'Адрес')]");
    //поле выбора станции метро
    private By metroInput = By.className("select-search__input");
    //поле ввода телефона
    private By phone = By.xpath(".//input[@class='Input_Input__1iN_Z Input_Responsible__1jDKN' and contains(@placeholder, 'Телефон')]");
    //кнопка "Далее"
    private By nextButton = By.cssSelector("button.Button_Button__ra12g.Button_Middle__1CSJM");

    //заголовок месяца в календаре
    By monthHeader = By.className("react-datepicker__current-month");
    //кнопка "следующий месяц"
    By nextMonthButton = By.className("react-datepicker__navigation--next");
    //кнопка "предыдущий месяц"
    By prevMonthButton = By.className("react-datepicker__navigation--previous");
    //календарь "Когда привезти Самокат"
    private By deliveryDateInput = By.cssSelector("input[placeholder*='Когда привезти']");
    //пустой дропдаун выбора срока аренды
    private By emptyRentTimeField = By.xpath(".//div[@class='Dropdown-placeholder' and contains(text(),'Срок аренды')]");
    //список всех элементов дропдауна выбора срока аренды
    private By rentTimeDropdownElements = By.xpath(".//div[@class='Dropdown-menu']/div");
    //Заполненный дропдаун выбора срока аренды
    private By filledRentTimeDropdown = By.xpath(".//div[@class='Dropdown-placeholder is-selected']");
    //пустой блок выбора цвета самоката
    private By emptyOrderCheckboxes = By.xpath(".//div[@class='Order_Checkboxes__3lWSI']");
    //список кнопок выбора цвета самоката
    private By checkboxLabels = By.xpath(".//label[@class='Checkbox_Label__3wxSf']");
    //Заполненный блок выбора цвета самоката
    private By filledOrderCheckboxes = By.xpath(".//div[@class='Order_Checkboxes__3lWSI Order_FilledContainer__2MKAk']");
    //поле ввода комментария
    private By comment = By.xpath(".//input[@class='Input_Input__1iN_Z Input_Responsible__1jDKN' and contains(@placeholder, 'Комментарий')]");
    //кнопка "Заказать"
    private By orderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");
    //кнопка "Назад"
    private By BackButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM Button_Inverted__3IF-i' and text()='Назад']");

    //Текст модалки "Хотите оформить заказ?"
    private By modalText = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ']");
    //Кнопка "Нет" в модалке
    private By noModalButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM Button_Inverted__3IF-i' and text()='Нет']");
    //Кнопка "Да" в модалке
    private By yesModalButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']");
    //Модалка успешного оформления заказа
    private By SuccessModalPage = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ']");

    //метод проверки, что модальное окно оформления заказа появилось
    public void checkOrderFormAppeared() {
        if (!driver.findElement(orderForm).isDisplayed()) {
            throw new IllegalStateException("Модальное окно оформления заказа не отобразилось");
        }
    }
    //метод заполнения поля имени
    public void insertName(String firstName) {
        driver.findElement(name).sendKeys(firstName);
    }
    //метод заполнения поля фамилии
    public void insertSurname(String secondName) {
        driver.findElement(surname).sendKeys(secondName);
    }
    //метод заполнения поля адреса
    public void insertAddress(String LivingAddress) {
        driver.findElement(address).sendKeys(LivingAddress);
    }
    //метод заполнения поля станции метро
    public void insertMetro(String stationName) {
        WebElement input = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(metroInput));
        input.click(); // чтобы открыть дропдаун
        input.clear(); // на всякий случай
        input.sendKeys(stationName);
        // дождаться появления нужного элемента и кликнуть по нему
        By dropdownOption = By.xpath(".//div[@class='Order_Text__2broi' and text()='" + stationName + "']");
        WebElement option = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(dropdownOption));
        option.click();
    }
    //метод заполнения поля телефона
    public void insertPhone(String phoneNumber) {
        driver.findElement(phone).sendKeys(phoneNumber);
    }
    //метод нажатия кнопки "далее"
    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }
    //шаг заполнения 1 формы (персональные данные)
    public void fillFirstStep(String firstName, String secondName, String LivingAddress, String metroStation, String phoneNumber) {
        insertName(firstName);
        insertSurname(secondName);
        insertAddress(LivingAddress);
        insertMetro(metroStation);
        insertPhone(phoneNumber);
        clickNextButton();
    }

    //метод выбора дня в календаре
    public void selectDateByOffset(int dayOffset) {
        driver.findElement(deliveryDateInput).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        // 1. Целевая дата
        LocalDate targetDate = LocalDate.now().plusDays(dayOffset);
        int day = targetDate.getDayOfMonth();
        Month targetMonth = targetDate.getMonth();
        int targetYear = targetDate.getYear();
        // 2. Ждём появления календаря
        wait.until(ExpectedConditions.visibilityOfElementLocated(monthHeader));
        // 3. Определяем текущий месяц и год на календаре
        while (true) {
            WebElement header = driver.findElement(monthHeader);
            String displayedText = header.getText(); // например, "апрель 2025"
            // Парсим текст (используем русский Locale!)
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("LLLL yyyy", new Locale("ru"));
            YearMonth displayedYearMonth = YearMonth.parse(displayedText, formatter);
            YearMonth targetYearMonth = YearMonth.of(targetYear, targetMonth);
            if (displayedYearMonth.isBefore(targetYearMonth)) {
                driver.findElement(nextMonthButton).click();
                wait.until(ExpectedConditions.stalenessOf(header));
                wait.until(ExpectedConditions.visibilityOfElementLocated(monthHeader));
            } else if (displayedYearMonth.isAfter(targetYearMonth)) {
                driver.findElement(prevMonthButton).click();
                wait.until(ExpectedConditions.stalenessOf(header));
                wait.until(ExpectedConditions.visibilityOfElementLocated(monthHeader));
            } else {
                break; // месяц совпал
            }
        }
        // 4. Кликаем по дню (ищем только в пределах текущего месяца)
        String dayLocator = String.format(
                "//div[contains(@class,'react-datepicker__day') and not(contains(@class,'--outside-month')) and text()='%d']",
                day
        );
        WebElement targetDay = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dayLocator)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", targetDay);
        targetDay.click();
    }
    //метод заполнения поля даты доставки
    public void insertWhenToDeliver(String deliveryDate) {
        WebElement dateInput = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(deliveryDateInput));
        dateInput.sendKeys(deliveryDate);
    }
    //метод нажатия на поле с дропдауном выбора срока доставки
    public void clickEmptyRentTimeField() {
        driver.findElement(emptyRentTimeField).click();
    }
    //метод проверки, что дропдаун появился и клик на выбранный элемент, проверка подстановки значения
    public void selectVisibleDropdownElementByIndex(int dropdownIndex) {
        List<WebElement> elements = driver.findElements(rentTimeDropdownElements);
        if (dropdownIndex < 0 || dropdownIndex >= elements.size()) {
            throw new IllegalArgumentException("Недопустимый индекс: " + dropdownIndex);
        }
        WebElement element = elements.get(dropdownIndex);
        if (!element.isDisplayed()) {
            throw new IllegalStateException("Элемент по индексу " + dropdownIndex + " не отображается на странице");
        }
        element.click();
        if (!driver.findElement(filledRentTimeDropdown).isDisplayed()) {
            throw new IllegalStateException("Поле выбора срока доставки не удалось заполнить");
        }
    }
    //метод проверки, что список чекбоксов есть, клик на выбранный элемент, проверка что чекбокс заполнился
    public void selectVisibleCheckboxElementByIndex(int checkboxIndex) {
        List<WebElement> elements = driver.findElements(checkboxLabels);
        if (checkboxIndex < 0 || checkboxIndex >= elements.size()) {
            throw new IllegalArgumentException("Недопустимый индекс: " + checkboxIndex);
        }
        WebElement element = elements.get(checkboxIndex);
        if (!driver.findElement(emptyOrderCheckboxes).isDisplayed()) {
            throw new IllegalStateException("Блок с чекбоксами не отображается на странице");
        }
        element.click();
        if (!driver.findElement(filledOrderCheckboxes).isDisplayed()) {
            throw new IllegalStateException("Не удалось проставить чекбокс");
        }
    }
    //метод заполнения поля комментария
    public void insertComment(String commentText) {
        driver.findElement(comment).sendKeys(commentText);
    }
    //метод нажатия кнопки "Назад"
    public void clickBackButton() {
        driver.findElement(BackButton).click();
    }
    //метод нажатия кнопки "Заказать", проверка отображения модального окна подтверждения заказа
    public void clickOrderButton() {
        driver.findElement(orderButton).click();
        if (!driver.findElement(modalText).isDisplayed()) {
            throw new IllegalStateException("Модальное окно подтверждения заказа не отобразилось");
        }
    }
    //метод нажатия кнопки "Нет"
    public void clickNoButton() {
        driver.findElement(noModalButton).click();
    }
    //метод нажатия кнопки "Да"
    public void clickYesButton() {
        driver.findElement(yesModalButton).click();
    }
    //метод проверки текста на success page
    public String getTextSuccessModalPage() {
        return driver.findElement(SuccessModalPage).getText();
    }
    //шаг заполнения 2 формы (данные о заказе) + клик "Да" в модалке подтверждения
    public void fillSecondStep(int dateOffset, int dropdownIndex, int checkboxIndex, String commentText) {
        selectDateByOffset(dateOffset);
        clickEmptyRentTimeField();
        selectVisibleDropdownElementByIndex(dropdownIndex);
        selectVisibleCheckboxElementByIndex(checkboxIndex);
        insertComment(commentText);
        clickOrderButton();
        clickYesButton();
    }
}