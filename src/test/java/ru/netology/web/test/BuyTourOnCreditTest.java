package ru.netology.web.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.web.data.DataHelper;
import ru.netology.web.data.SQLHelper;
import ru.netology.web.page.PageObject;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static ru.netology.web.data.SQLHelper.cleanDataBase;

public class BuyTourOnCreditTest {
    PageObject page;

    @BeforeAll
    static void setUpAll() {
        // Добавляем листенер в тестовый класс перед выполнением всех тестов
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        // Удаляем листенер после выполнения всех тестов
        SelenideLogger.removeListener("allure");
    }

    @AfterEach
    void tearDownAllDataBase() {
        cleanDataBase();
    }

    @BeforeEach
    void setUp() {
        page = open("http://localhost:8080", PageObject.class);
        page.selectBuyInCredit();
    }

    @Test
    void shouldSuccessfulBuyTourInCreditCardStatusApproved() {
        page.inputDataFieldsFormCard(DataHelper.getCardStatusApproved());
        page.verifySuccessfulNotification();

        assertEquals("APPROVED", SQLHelper.getStatusCreditRequestEntity());
    }

    @Test
    void shouldRefusalBuyTourInCreditCardStatusDeclined() {
        page.inputDataFieldsFormCard(DataHelper.getCardStatusDeclined());
        page.verifyErrorNotification();

        assertEquals("DECLINED", SQLHelper.getStatusCreditRequestEntity());
    }

    @Test
    void shouldCardEmptyFields() {
        page.inputDataFieldsFormCard(DataHelper.getCardEmptyFields());
        page.verifyErrorFieldCardNumber();
        page.verifyErrorFieldMonth();
        page.verifyErrorFieldYear();
        page.verifyErrorFieldOwner();
        page.verifyErrorFieldCVC();

        assertNull(SQLHelper.getStatusCreditRequestEntity());
    }

    @Test
    void shouldCardNumberEmpty() {
        page.inputDataFieldsFormCard(DataHelper.getCardNumberEmpty());
        page.verifyErrorFieldCardNumber();

        assertNull(SQLHelper.getIdOrderEntity());
    }

    @Test
    void shouldCardNumber15Digits() {
        page.inputDataFieldsFormCard(DataHelper.getCardNumber15Digits());
        page.verifyErrorFieldCardNumber();

        assertNull(SQLHelper.getIdOrderEntity());
    }

    @Test
    void shouldCardNumber17Digits() {
        page.inputDataFieldsFormCard(DataHelper.getCardNumber17Digits());
        page.verifyErrorNotification();

        assertNull(SQLHelper.getIdOrderEntity());
    }

    @Test
    void shouldCardNumber16Symbols() {
        page.inputDataFieldsFormCard(DataHelper.getCardNumber16Symbols());
        page.verifyErrorFieldCardNumber();

        assertNull(SQLHelper.getIdOrderEntity());
    }

    @Test
    void shouldMonthEmpty() {
        page.inputDataFieldsFormCard(DataHelper.getMonthEmpty());
        page.verifyErrorFieldMonth();

        assertNull(SQLHelper.getIdOrderEntity());
    }

    @Test
    void shouldMonthOneDigital() {
        page.inputDataFieldsFormCard(DataHelper.getMonthOneDigital());
        page.verifyErrorFieldMonth();

        assertNull(SQLHelper.getIdOrderEntity());
    }

    @Test
    void shouldMonthTwoZero() {
        page.inputDataFieldsFormCard(DataHelper.getMonthTwoZero());
        page.verifyErrorFieldMonth();

        assertNull(SQLHelper.getIdOrderEntity());
    }

    @Test
    void shouldMonthLessCurrentThisYear() {
        page.inputDataFieldsFormCard(DataHelper.getMonthLessCurrentThisYear());
        page.verifyErrorFieldMonthLessCurrentThisYear();

        assertNull(SQLHelper.getIdOrderEntity());
    }

    @Test
    void shouldMonthMore12() {
        page.inputDataFieldsFormCard(DataHelper.getMonthMore12());
        page.verifyErrorFieldMonthLessCurrentThisYear();

        assertNull(SQLHelper.getIdOrderEntity());
    }

    @Test
    void shouldYearEmpty() {
        page.inputDataFieldsFormCard(DataHelper.getYearEmpty());
        page.verifyErrorFieldYear();

        assertNull(SQLHelper.getIdOrderEntity());
    }

    @Test
    void shouldYearOneDigital() {
        page.inputDataFieldsFormCard(DataHelper.getYearOneDigital());
        page.verifyErrorFieldYear();

        assertNull(SQLHelper.getIdOrderEntity());
    }

    @Test
    void shouldYearLessCurrent() {
        page.inputDataFieldsFormCard(DataHelper.getYearLessCurrent());
        page.verifyErrorFieldYearLessCurrent();

        assertNull(SQLHelper.getIdOrderEntity());
    }

    @Test
    void shouldYearMoreCurrentBy6() {
        page.inputDataFieldsFormCard(DataHelper.getYearMoreCurrentBy6());
        page.verifyErrorFieldYearMore6FromCurrent();

        assertNull(SQLHelper.getIdOrderEntity());
    }

    @Test
    void shouldOwnerCardEmpty() {
        page.inputDataFieldsFormCard(DataHelper.getOwnerCardEmpty());
        page.verifyErrorFieldOwner();

        assertNull(SQLHelper.getIdOrderEntity());
    }

    @Test
    void shouldOwnerCardOneSymbol() {
        page.inputDataFieldsFormCard(DataHelper.getOwnerCardOneSymbol());
        page.verifyErrorFieldOwner();

        assertNull(SQLHelper.getIdOrderEntity());
    }

    @Test
    void shouldOwnerCardCyrillic() {
        page.inputDataFieldsFormCard(DataHelper.getOwnerCardCyrillic());
        page.verifyErrorFieldOwner();

        assertNull(SQLHelper.getIdOrderEntity());
    }

    @Test
    void shouldCvcEmpty() {
        page.inputDataFieldsFormCard(DataHelper.getCvcEmpty());
        page.verifyErrorFieldCVC();

        assertNull(SQLHelper.getIdOrderEntity());
    }

    @Test
    void shouldCvcTwoDigital() {
        page.inputDataFieldsFormCard(DataHelper.getCvcTwoDigital());
        page.verifyErrorFieldCVC();

        assertNull(SQLHelper.getIdOrderEntity());
    }

}
