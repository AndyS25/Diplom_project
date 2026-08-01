package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.CardInfo;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class PageObject {
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
        SelenideElement heading = $("h2.heading");
        heading.shouldBe(visible);
    }

    public void selectBuy() {
        buttonBuy.click();
        headingBuy.shouldBe(visible);
    }

    public void selectBuyInCredit() {
        buttonBuyOnCredit.click();
        headingBuyOnCredit.shouldBe(visible);
    }

    public void inputDataFieldsFormCard(CardInfo card) {
        fieldNumberCard.setValue(card.getNumberCard());
        fieldMonth.setValue(card.getMonth());
        fieldYear.setValue(card.getYear());
        fieldOwner.setValue(card.getOwnerCard());
        fieldCVC.setValue(card.getCvc());
        buttonContinue.click();
    }

    public void verifySuccessfulNotification() {
        notificationSuccessfully.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void verifyErrorNotification() {
        notificationError.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void verifyErrorFieldCardNumber() {
        errorFieldCardNumber.shouldBe(visible);
    }

    public void verifyErrorFieldMonth() {
        errorFieldMonth.shouldBe(visible);
    }

    public void verifyErrorFieldMonthLessCurrentThisYear() {
        errorFieldMonthLessCurrentThisYear.shouldBe(visible);
    }

    public void verifyErrorFieldYear() {
        errorFieldYear.shouldBe(visible);
    }

    public void verifyErrorFieldYearLessCurrent() {
        errorFieldYearLessCurrent.shouldBe(visible);
    }

    public void verifyErrorFieldYearMore6FromCurrent() {
        errorFieldYearMore6FromCurrent.shouldBe(visible);
    }

    public void verifyErrorFieldOwner() {
        errorFieldOwner.shouldBe(visible);
    }

    public void verifyErrorFieldCVC() {
        errorFieldCVC.shouldBe(visible);
    }

}