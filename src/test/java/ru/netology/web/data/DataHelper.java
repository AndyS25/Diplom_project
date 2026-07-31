package ru.netology.web.data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {

    private static final Faker FAKER = new Faker(new Locale("en"));

    private DataHelper() {
    }

    public static CardInfo getCardStatusApproved() {
        return new CardInfo("4444444444444441", generateMonth(1), generateYear(1),
                "Ivan Makarov", "123");
    }

    public static CardInfo getCardStatusDeclined() {
        return new CardInfo("4444444444444442", generateMonth(1), generateYear(1),
                "Ivan Makarov", "123");
    }

    public static CardInfo getCardEmptyFields() {
        return new CardInfo("", "", "", "", "");
    }

    public static String getRandomCardNumber() {
        return FAKER.business().creditCardNumber();
    }

    public static String generateMonth(int value) {
        return LocalDate.now().plusMonths(value).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String generateYear(int value) {
        return LocalDate.now().plusYears(value).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String generateOwnerCard() {
        return FAKER.name().firstName() + " " + FAKER.name().lastName();
    }

    public static String generateCVC() {
        return FAKER.numerify(("###"));
    }

    public static CardInfo getCardNumberEmpty() {
        return new CardInfo("", generateMonth(1), generateYear(1),
                generateOwnerCard(), generateCVC());
    }

    public static CardInfo getCardNumber15Digits() {
        return new CardInfo("444444444444444", generateMonth(1), generateYear(1),
                generateOwnerCard(), generateCVC());
    }

    public static CardInfo getCardNumber17Digits() {
        return new CardInfo("44444444444444444", generateMonth(1), generateYear(1),
                generateOwnerCard(), generateCVC());
    }

    public static CardInfo getCardNumber16Symbols() {
        return new CardInfo("AAAABBBBCCCCDDDD", generateMonth(1), generateYear(1),
                generateOwnerCard(), generateCVC());
    }

    //поле месяц
    public static CardInfo getMonthEmpty() {
        return new CardInfo(getRandomCardNumber(), "", generateYear(1), generateOwnerCard(), generateCVC());
    }

    public static CardInfo getMonthOneDigital() {
        return new CardInfo(getRandomCardNumber(), "1", generateYear(1), generateOwnerCard(), generateCVC());
    }

    public static CardInfo getMonthTwoZero() {
        return new CardInfo(getRandomCardNumber(), "00", generateYear(1), generateOwnerCard(), generateCVC());
    }

    public static CardInfo getMonthLessCurrentThisYear() {
        return new CardInfo(getRandomCardNumber(), generateMonth(-1), generateYear(0), generateOwnerCard(), generateCVC());
    }

    public static CardInfo getMonthMore12() {
        return new CardInfo(getRandomCardNumber(), "13", generateYear(1), generateOwnerCard(), generateCVC());
    }

    //поле год
    public static CardInfo getYearEmpty() {
        return new CardInfo(getRandomCardNumber(), generateMonth(1), "", generateOwnerCard(), generateCVC());
    }

    public static CardInfo getYearOneDigital() {
        return new CardInfo(getRandomCardNumber(), generateMonth(1), "2", generateOwnerCard(), generateCVC());
    }

    public static CardInfo getYearLessCurrent() {
        return new CardInfo(getRandomCardNumber(), generateMonth(1), generateYear(-1), generateOwnerCard(), generateCVC());
    }

    public static CardInfo getYearMoreCurrentBy6() {
        return new CardInfo(getRandomCardNumber(), generateMonth(0), generateYear(6), generateOwnerCard(), generateCVC());
    }

    //поле владелец
    public static CardInfo getOwnerCardEmpty() {
        return new CardInfo(getRandomCardNumber(), generateMonth(1), generateYear(1), "", generateCVC());
    }

    public static CardInfo getOwnerCardOneSymbol() {
        return new CardInfo(getRandomCardNumber(), generateMonth(1), generateYear(1), "A", generateCVC());
    }

    public static CardInfo getOwnerCardCyrillic() {
        return new CardInfo(getRandomCardNumber(), generateMonth(1), generateYear(1), "Петр Иванов", generateCVC());
    }

    //поле CVC
    public static CardInfo getCvcEmpty() {
        return new CardInfo(getRandomCardNumber(), generateMonth(1), generateYear(1), generateOwnerCard(), "");
    }

    public static CardInfo getCvcTwoDigital() {
        return new CardInfo(getRandomCardNumber(), generateMonth(1), generateYear(1), generateOwnerCard(), "12");
    }

}
