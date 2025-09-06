package com.comarch.szkolenia.book.store.validators;

import com.comarch.szkolenia.book.store.exceptions.OrderValidationException;
import com.comarch.szkolenia.book.store.model.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderValidatorTest {

    @Test
    public void validateNullCityTest() {
        String city = null;

        Assertions.assertThrows(
                OrderValidationException.class,
                () -> OrderValidator.validateCity(city));
    }

    @Test
    public void emptyCityTest() {
        String city = "";

        Assertions.assertThrows(
                OrderValidationException.class,
                () -> OrderValidator.validateCity(city));
    }

    @Test
    public void cityWithLowercaseFirstLetterTest() {
        String city = "warszawa";

        Assertions.assertThrows(
                OrderValidationException.class,
                () -> OrderValidator.validateCity(city));
    }

    @Test
    public void cityWithNumbersTest() {
        String city = "Warszawa123";

        Assertions.assertThrows(
                OrderValidationException.class,
                () -> OrderValidator.validateCity(city));
    }

    @Test
    public void correctCityTest() {
        String city = "Warszawa";

        Assertions.assertDoesNotThrow(
                () -> OrderValidator.validateCity(city));
    }

    @Test
    public void validateNullStreetTest() {
        String street = null;

        Assertions.assertThrows(
                OrderValidationException.class,
                () -> OrderValidator.validateStreet(street));
    }

    @Test
    public void emptyStreetTest() {
        String street = "";

        Assertions.assertThrows(
                OrderValidationException.class,
                () -> OrderValidator.validateStreet(street));
    }

    @Test
    public void streetWithLowercaseFirstLetterTest() {
        String street = "marszałkowska";

        Assertions.assertThrows(
                OrderValidationException.class,
                () -> OrderValidator.validateStreet(street));
    }

    @Test
    public void streetWithNumbersTest() {
        String street = "Marszałkowska123";

        Assertions.assertThrows(
                OrderValidationException.class,
                () -> OrderValidator.validateStreet(street));
    }

    @Test
    public void correctStreetTest() {
        String street = "Marszałkowska";

        Assertions.assertDoesNotThrow(
                () -> OrderValidator.validateStreet(street));
    }

    @Test
    public void validateNullNoTest() {
        String no = null;

        Assertions.assertThrows(
                OrderValidationException.class,
                () -> OrderValidator.validateNo(no));
    }

    @Test
    public void emptyNoTest() {
        String no = "";

        Assertions.assertThrows(
                OrderValidationException.class,
                () -> OrderValidator.validateNo(no));
    }

    @Test
    public void noWithOnlyLettersTest() {
        String no = "ABC";

        Assertions.assertThrows(
                OrderValidationException.class,
                () -> OrderValidator.validateNo(no));
    }

    @Test
    public void noWithSpecialCharactersTest() {
        String no = "12@#";

        Assertions.assertThrows(
                OrderValidationException.class,
                () -> OrderValidator.validateNo(no));
    }

    @Test
    public void correctSimpleNoTest() {
        String no = "12";

        Assertions.assertDoesNotThrow(
                () -> OrderValidator.validateNo(no));
    }

    @Test
    public void correctNoWithLetterTest() {
        String no = "12A";

        Assertions.assertDoesNotThrow(
                () -> OrderValidator.validateNo(no));
    }

    @Test
    public void correctNoWithApartmentTest() {
        String no = "12/5";

        Assertions.assertDoesNotThrow(
                () -> OrderValidator.validateNo(no));
    }

    @Test
    public void correctNoWithLetterAndApartmentTest() {
        String no = "12A/5";

        Assertions.assertDoesNotThrow(
                () -> OrderValidator.validateNo(no));
    }

    @Test
    public void validateNullPostCodeTest() {
        String postCode = null;

        Assertions.assertThrows(
                OrderValidationException.class,
                () -> OrderValidator.validatePostCode(postCode));
    }

    @Test
    public void emptyPostCodeTest() {
        String postCode = "";

        Assertions.assertThrows(
                OrderValidationException.class,
                () -> OrderValidator.validatePostCode(postCode));
    }

    @Test
    public void postCodeWithoutHyphenTest() {
        String postCode = "12345";

        Assertions.assertThrows(
                OrderValidationException.class,
                () -> OrderValidator.validatePostCode(postCode));
    }

    @Test
    public void postCodeWithLettersTest() {
        String postCode = "AB-CDE";

        Assertions.assertThrows(
                OrderValidationException.class,
                () -> OrderValidator.validatePostCode(postCode));
    }

    @Test
    public void postCodeTooShortTest() {
        String postCode = "1-234";

        Assertions.assertThrows(
                OrderValidationException.class,
                () -> OrderValidator.validatePostCode(postCode));
    }

    @Test
    public void postCodeTooLongTest() {
        String postCode = "123-4567";

        Assertions.assertThrows(
                OrderValidationException.class,
                () -> OrderValidator.validatePostCode(postCode));
    }

    @Test
    public void correctPostCodeTest() {
        String postCode = "12-345";

        Assertions.assertDoesNotThrow(
                () -> OrderValidator.validatePostCode(postCode));
    }

    @Test
    public void validateNullPhoneNumberTest() {
        String phoneNumber = null;

        Assertions.assertThrows(
                OrderValidationException.class,
                () -> OrderValidator.validatePhoneNumber(phoneNumber));
    }

    @Test
    public void emptyPhoneNumberTest() {
        String phoneNumber = "";

        Assertions.assertThrows(
                OrderValidationException.class,
                () -> OrderValidator.validatePhoneNumber(phoneNumber));
    }

    @Test
    public void phoneNumberTooShortTest() {
        String phoneNumber = "12345678";

        Assertions.assertThrows(
                OrderValidationException.class,
                () -> OrderValidator.validatePhoneNumber(phoneNumber));
    }

    @Test
    public void phoneNumberTooLongTest() {
        String phoneNumber = "1234567890";

        Assertions.assertThrows(
                OrderValidationException.class,
                () -> OrderValidator.validatePhoneNumber(phoneNumber));
    }

    @Test
    public void phoneNumberWithLettersTest() {
        String phoneNumber = "12345678A";

        Assertions.assertThrows(
                OrderValidationException.class,
                () -> OrderValidator.validatePhoneNumber(phoneNumber));
    }

    @Test
    public void phoneNumberWithInvalidPrefixTest() {
        String phoneNumber = "+49123456789";

        Assertions.assertThrows(
                OrderValidationException.class,
                () -> OrderValidator.validatePhoneNumber(phoneNumber));
    }

    @Test
    public void correctPhoneNumberTest() {
        String phoneNumber = "123456789";

        Assertions.assertDoesNotThrow(
                () -> OrderValidator.validatePhoneNumber(phoneNumber));
    }

    @Test
    public void correctPhoneNumberWithPrefixTest() {
        String phoneNumber = "+48123456789";

        Assertions.assertDoesNotThrow(
                () -> OrderValidator.validatePhoneNumber(phoneNumber));
    }

    @Test
    public void validateCorrectOrderTest() {
        Order order = new Order();
        order.setCity("Warszawa");
        order.setStreet("Marszałkowska");
        order.setNo("12A");
        order.setPostCode("00-123");
        order.setPhoneNumber("123456789");

        Assertions.assertDoesNotThrow(
                () -> OrderValidator.validateOrder(order));
    }
}
