package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.CardInfo;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class PageObject {
    private final SelenideElement heading = $("h2.heading");
    private final SelenideElement headingBuy = $(byText("Оплата по карте"));
    private final SelenideElement headingBuyOnCredit = $(byText("Кредит по данным карты"));

    private final SelenideElement buttonBuy = $(byText("Купить"));
    private final SelenideElement buttonBuyOnCredit = $(byText("Купить в кредит"));
    private final SelenideElement buttonContinue = $(byText("Продолжить"));

    private final SelenideElement fieldNumberCard = $("[placeholder='0000 0000 0000 0000']");
    private final SelenideElement fieldMonth = $("[placeholder='08']");
    private final SelenideElement fieldYear = $("[placeholder='22']");
    private final SelenideElement fieldOwner = $$("[class=input__inner]")
            .findBy(text("Владелец")).$("[class=input__control");
    private final SelenideElement fieldCVC = $("[placeholder='999']");

    private final SelenideElement notificationSuccessfully = $(byText("Операция одобрена Банком."));
    private final SelenideElement notificationError = $(byText("Ошибка! Банк отказал в проведении операции."));

    private final SelenideElement errorFieldCardNumber = $$("[class=input__inner]")
            .findBy(text("Номер карты")).$(byText("Неверный формат"));
    private final SelenideElement errorFieldMonth = $$("[class=input__inner]").findBy(text("Месяц"))
            .$(byText("Неверный формат"));
    private final SelenideElement errorFieldMonthLessCurrentThisYear = $$("[class=input__inner]").findBy(text("Месяц"))
            .$(byText("Неверно указан срок действия карты"));
    private final SelenideElement errorFieldYear = $$("[class=input__inner]").findBy(text("Год"))
            .$(byText("Неверный формат"));
    private final SelenideElement errorFieldYearLessCurrent = $$("[class=input__inner]").findBy(text("Год"))
            .$(byText("Истёк срок действия карты"));
    private final SelenideElement errorFieldYearMore6FromCurrent = $$("[class=input__inner]").findBy(text("Год"))
            .$(byText("Неверно указан срок действия карты"));
    private final SelenideElement errorFieldOwner = $$("[class=input__inner]").findBy(text("Владелец"))
            .$(byText("Поле обязательно для заполнения"));
    private final SelenideElement errorFieldCVC = $$("[class=input__inner]").findBy(text("CVC/CVV"))
            .$(byText("Неверный формат"));

    public PageObject() {
        heading.shouldBe(visible);
    }

    public void selectBuy(String expectedText) {
        buttonBuy.click();
        headingBuy.shouldHave(exactText(expectedText)).shouldBe(visible);
    }

    public void selectBuyInCredit(String expectedText) {
        buttonBuyOnCredit.click();
        headingBuyOnCredit.shouldHave(exactText(expectedText)).shouldBe(visible);
    }

    public void inputDataFieldsFormCard(CardInfo card) {
        fieldNumberCard.setValue(card.getNumberCard());
        fieldMonth.setValue(card.getMonth());
        fieldYear.setValue(card.getYear());
        fieldOwner.setValue(card.getOwnerCard());
        fieldCVC.setValue(card.getCvc());
        buttonContinue.click();
    }

    public void verifySuccessfulNotification(String expectedText) {
        notificationSuccessfully.shouldBe(visible, Duration.ofSeconds(15)).shouldHave(exactText(expectedText));
    }

    public void verifyErrorNotification(String expectedText) {
        notificationError.shouldBe(visible, Duration.ofSeconds(15)).shouldHave(exactText(expectedText));
    }

    public void verifyErrorFieldCardNumber(String expectedText) {
        errorFieldCardNumber.shouldBe(visible).shouldHave(exactText(expectedText));
    }

    public void verifyErrorFieldMonth(String expectedText) {
        errorFieldMonth.shouldBe(visible).shouldHave(exactText(expectedText));
    }

    public void verifyErrorFieldMonthLessCurrentThisYear(String expectedText) {
        errorFieldMonthLessCurrentThisYear.shouldBe(visible).shouldHave(exactText(expectedText));
    }

    public void verifyErrorFieldYear(String expectedText) {
        errorFieldYear.shouldBe(visible).shouldHave(exactText(expectedText));
    }

    public void verifyErrorFieldYearLessCurrent(String expectedText) {
        errorFieldYearLessCurrent.shouldBe(visible).shouldHave(exactText(expectedText));
    }

    public void verifyErrorFieldYearMore6FromCurrent(String expectedText) {
        errorFieldYearMore6FromCurrent.shouldBe(visible).shouldHave(exactText(expectedText));
    }

    public void verifyErrorFieldOwner(String expectedText) {
        errorFieldOwner.shouldBe(visible).shouldHave(exactText(expectedText));
    }

    public void verifyErrorFieldCVC(String expectedText) {
        errorFieldCVC.shouldBe(visible).shouldHave(exactText(expectedText));
    }

}