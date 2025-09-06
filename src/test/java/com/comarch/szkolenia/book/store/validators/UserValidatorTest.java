package com.comarch.szkolenia.book.store.validators;

import com.comarch.szkolenia.book.store.exceptions.UserValidationException;
import com.comarch.szkolenia.book.store.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserValidatorTest {

    @Test
    public void validateNullLoginTest() {
        String login = null;

        Assertions.assertThrows(
                UserValidationException.class,
                () -> UserValidator.validateLogin(login));
    }

    @Test
    public void emptyLoginTest() {
        String login = "";

        Assertions.assertThrows(
                UserValidationException.class,
                () -> UserValidator.validateLogin(login));
    }

    @Test
    public void loginTooShortTest() {
        String login = "abc";

        Assertions.assertThrows(
                UserValidationException.class,
                () -> UserValidator.validateLogin(login));
    }

    @Test
    public void loginWithSpecialCharactersTest() {
        String login = "user@123";

        Assertions.assertThrows(
                UserValidationException.class,
                () -> UserValidator.validateLogin(login));
    }

    @Test
    public void loginWithSpacesTest() {
        String login = "user name";

        Assertions.assertThrows(
                UserValidationException.class,
                () -> UserValidator.validateLogin(login));
    }

    @Test
    public void correctLoginTest() {
        String login = "user123";

        Assertions.assertDoesNotThrow(
                () -> UserValidator.validateLogin(login));
    }

    @Test
    public void correctLoginWithUnderscoreTest() {
        String login = "user_123";

        Assertions.assertDoesNotThrow(
                () -> UserValidator.validateLogin(login));
    }

    @Test
    public void validateNullPasswordTest() {
        String password = null;

        Assertions.assertThrows(
                UserValidationException.class,
                () -> UserValidator.validatePassword(password));
    }

    @Test
    public void emptyPasswordTest() {
        String password = "";

        Assertions.assertThrows(
                UserValidationException.class,
                () -> UserValidator.validatePassword(password));
    }

    @Test
    public void passwordTooShortTest() {
        String password = "1234";

        Assertions.assertThrows(
                UserValidationException.class,
                () -> UserValidator.validatePassword(password));
    }

    @Test
    public void passwordWithSpecialCharactersTest() {
        String password = "pass@123";

        Assertions.assertThrows(
                UserValidationException.class,
                () -> UserValidator.validatePassword(password));
    }

    @Test
    public void passwordWithSpacesTest() {
        String password = "pass word";

        Assertions.assertThrows(
                UserValidationException.class,
                () -> UserValidator.validatePassword(password));
    }

    @Test
    public void correctPasswordTest() {
        String password = "password123";

        Assertions.assertDoesNotThrow(
                () -> UserValidator.validatePassword(password));
    }

    @Test
    public void correctPasswordWithUnderscoreTest() {
        String password = "pass_123";

        Assertions.assertDoesNotThrow(
                () -> UserValidator.validatePassword(password));
    }

    @Test
    public void validateNullNameTest() {
        String name = null;

        Assertions.assertThrows(
                UserValidationException.class,
                () -> UserValidator.validateName(name));
    }

    @Test
    public void emptyNameTest() {
        String name = "";

        Assertions.assertThrows(
                UserValidationException.class,
                () -> UserValidator.validateName(name));
    }

    @Test
    public void nameWithLowercaseFirstLetterTest() {
        String name = "jan";

        Assertions.assertThrows(
                UserValidationException.class,
                () -> UserValidator.validateName(name));
    }

    @Test
    public void nameTooShortTest() {
        String name = "Jo";

        Assertions.assertThrows(
                UserValidationException.class,
                () -> UserValidator.validateName(name));
    }

    @Test
    public void nameWithNumbersTest() {
        String name = "Jan123";

        Assertions.assertThrows(
                UserValidationException.class,
                () -> UserValidator.validateName(name));
    }

    @Test
    public void nameWithSpecialCharactersTest() {
        String name = "Jan@";

        Assertions.assertThrows(
                UserValidationException.class,
                () -> UserValidator.validateName(name));
    }

    @Test
    public void nameWithSpacesTest() {
        String name = "Jan Kowal";

        Assertions.assertThrows(
                UserValidationException.class,
                () -> UserValidator.validateName(name));
    }

    @Test
    public void correctNameTest() {
        String name = "Jan";

        Assertions.assertDoesNotThrow(
                () -> UserValidator.validateName(name));
    }

    @Test
    public void validateNullSurnameTest() {
        String surname = null;

        Assertions.assertThrows(
                UserValidationException.class,
                () -> UserValidator.validateSurname(surname));
    }

    @Test
    public void emptySurnameTest() {
        String surname = "";

        Assertions.assertThrows(
                UserValidationException.class,
                () -> UserValidator.validateSurname(surname));
    }

    @Test
    public void surnameWithLowercaseFirstLetterTest() {
        String surname = "kowalski";

        Assertions.assertThrows(
                UserValidationException.class,
                () -> UserValidator.validateSurname(surname));
    }

    @Test
    public void surnameWithNumbersTest() {
        String surname = "Kowalski123";

        Assertions.assertThrows(
                UserValidationException.class,
                () -> UserValidator.validateSurname(surname));
    }

    @Test
    public void surnameWithSpecialCharactersTest() {
        String surname = "Kowalski@";

        Assertions.assertThrows(
                UserValidationException.class,
                () -> UserValidator.validateSurname(surname));
    }

    @Test
    public void correctSurnameTest() {
        String surname = "Kowalski";

        Assertions.assertDoesNotThrow(
                () -> UserValidator.validateSurname(surname));
    }

    @Test
    public void correctSurnameWithHyphenTest() {
        String surname = "Kowalski-Nowak";

        Assertions.assertDoesNotThrow(
                () -> UserValidator.validateSurname(surname));
    }

    @Test
    public void correctSurnameWithSpaceTest() {
        String surname = "Kowalski Nowak";

        Assertions.assertDoesNotThrow(
                () -> UserValidator.validateSurname(surname));
    }

    @Test
    public void surnameWithInvalidHyphenFormatTest() {
        String surname = "Kowalski-nowak";

        Assertions.assertThrows(
                UserValidationException.class,
                () -> UserValidator.validateSurname(surname));
    }

    @Test
    public void surnameWithInvalidSpaceFormatTest() {
        String surname = "Kowalski nowak";

        Assertions.assertThrows(
                UserValidationException.class,
                () -> UserValidator.validateSurname(surname));
    }

    @Test
    public void validateNullAgeTest() {
        Integer age = null;

        Assertions.assertThrows(
                UserValidationException.class,
                () -> UserValidator.validateAge(age));
    }

    @Test
    public void zeroAgeTest() {
        Integer age = 0;

        Assertions.assertThrows(
                UserValidationException.class,
                () -> UserValidator.validateAge(age));
    }

    @Test
    public void negativeAgeTest() {
        Integer age = -5;

        Assertions.assertThrows(
                UserValidationException.class,
                () -> UserValidator.validateAge(age));
    }

    @Test
    public void ageTooHighTest() {
        Integer age = 121;

        Assertions.assertThrows(
                UserValidationException.class,
                () -> UserValidator.validateAge(age));
    }

    @Test
    public void correctMinAgeTest() {
        Integer age = 1;

        Assertions.assertDoesNotThrow(
                () -> UserValidator.validateAge(age));
    }

    @Test
    public void correctMaxAgeTest() {
        Integer age = 120;

        Assertions.assertDoesNotThrow(
                () -> UserValidator.validateAge(age));
    }

    @Test
    public void correctAgeTest() {
        Integer age = 25;

        Assertions.assertDoesNotThrow(
                () -> UserValidator.validateAge(age));
    }

    @Test
    public void checkPasswordsMatchNullPasswordTest() {
        String password = null;
        String confirmPassword = "password123";

        Assertions.assertThrows(
                UserValidationException.class,
                () -> UserValidator.checkPasswordsMatch(password, confirmPassword));
    }

    @Test
    public void checkPasswordsMatchNullConfirmPasswordTest() {
        String password = "password123";
        String confirmPassword = null;

        Assertions.assertThrows(
                UserValidationException.class,
                () -> UserValidator.checkPasswordsMatch(password, confirmPassword));
    }

    @Test
    public void checkPasswordsMatchDifferentPasswordsTest() {
        String password = "password123";
        String confirmPassword = "password456";

        Assertions.assertThrows(
                UserValidationException.class,
                () -> UserValidator.checkPasswordsMatch(password, confirmPassword));
    }

    @Test
    public void checkPasswordsMatchCorrectTest() {
        String password = "password123";
        String confirmPassword = "password123";

        Assertions.assertDoesNotThrow(
                () -> UserValidator.checkPasswordsMatch(password, confirmPassword));
    }

    @Test
    public void validateCorrectUserTest() {
        User user = new User();
        user.setLogin("user123");
        user.setPassword("password123");
        user.setName("Jan");
        user.setSurname("Kowalski");
        user.setAge(25);

        Assertions.assertDoesNotThrow(
                () -> UserValidator.validateUser(user));
    }
}
