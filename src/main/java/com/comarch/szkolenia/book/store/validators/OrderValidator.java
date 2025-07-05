package com.comarch.szkolenia.book.store.validators;

import com.comarch.szkolenia.book.store.exceptions.OrderValidationException;
import com.comarch.szkolenia.book.store.model.Order;

public class OrderValidator {
    public static void validateCity(String city) {
        String regex = "^[A-ZĄĆĘŁŃÓŚŹŻ][a-ząćęłńóśźż\\- ]+$";
        if (city == null || !city.matches(regex)) {
            throw new OrderValidationException("City must start with a capital letter and contain only letters, spaces or hyphens");
        }
    }

    public static void validateStreet(String street) {
        String regex = "^[A-ZĄĆĘŁŃÓŚŹŻ][a-ząćęłńóśźż\\- ]+$";
        if (street == null || !street.matches(regex)) {
            throw new OrderValidationException("Street must start with a capital letter and contain only letters, spaces or hyphens");
        }
    }

    public static void validateNo(String no) {
        String regex = "^\\d+[A-Z]?(\\/\\d+)?$";
        if (no == null || !no.matches(regex)) {
            throw new OrderValidationException("Number must be in format e.g. 34A or 34A/6");
        }
    }

    public static void validatePostCode(String postCode) {
        String regex = "^\\d{2}-\\d{3}$";
        if (postCode == null || !postCode.matches(regex)) {
            throw new OrderValidationException("Post code must be in format XX-XXX");
        }
    }

    public static void validatePhoneNumber(String phoneNumber) {
        String regex = "^(\\+48)?\\d{9}$";
        if (phoneNumber == null || !phoneNumber.matches(regex)) {
            throw new OrderValidationException("Phone number must be 9 digits, optionally starting with +48");
        }
    }

    public static void validateOrder(Order order) {
        validateCity(order.getCity());
        validateStreet(order.getStreet());
        validateNo(order.getNo());
        validatePostCode(order.getPostCode());
        validatePhoneNumber(order.getPhoneNumber());
    }
}
