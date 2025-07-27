function validateRegisterForm() {
    var login = document.getElementById("login-field");
    var password = document.getElementById("password-field");
    var password2 = document.getElementById("password2-field");
    var name = document.getElementById("name-field");
    var surname = document.getElementById("surname-field");
    var age = document.getElementById("age-field");

    var loginRegex = new RegExp("^\\w{4,}$");
    var passwordRegex = new RegExp("^\\w{5,}$");
    var nameRegex = new RegExp("^[A-Z]{1}[a-z]{2,}$");
    var surnameRegex = new RegExp("^[A-Z]{1}[a-z]+([ -]{1}[A-Z]{1}[a-z]+)?$");

    var result = true;

    if(!loginRegex.test(login.value)) {
        result = false;
        login.style.background = "#fac0c0";
    } else {
        login.style.background = null;
    }

    if(!passwordRegex.test(password.value) ||
    !passwordRegex.test(password2.value) ||
    password.value !== password2.value) {
        result = false;
        password.style.background = "#fac0c0";
        password2.style.background = "#fac0c0";
    } else {
        password.style.background = null;
        password2.style.background = null;
    }

    if(!nameRegex.test(name.value)) {
        result = false;
        name.style.background = "#fac0c0";
    } else {
        name.style.background = null;
    }

    if(!surnameRegex.test(surname.value)) {
        result = false;
        surname.style.background = "#fac0c0";
    } else {
        surname.style.background = null;
    }

    if(age.value < 1 || age.value > 120) {
        result = false;
        age.style.background = "#fac0c0";
    } else {
        age.style.background = null;
    }

    return result;
}

function validateOrderForm() {
    var city = document.getElementById("city-field");
    var street = document.getElementById("street-field");
    var no = document.getElementById("no-field");
    var postCode = document.getElementById("postCode-field");
    var phoneNumber = document.getElementById("phoneNumber-field");

    // Użycie literałów regex zamiast new RegExp i flagi 'u'
    var cityRegex = /^[A-ZĄĆĘŁŃÓŚŹŻ][a-ząćęłńóśźż\- ]{1,}$/;
    var streetRegex = /^[A-ZĄĆĘŁŃÓŚŹŻ][a-ząćęłńóśźż\- ]{1,}$/;
    var noRegex = /^\d+[A-Z]?(\/\d+)?$/;
    var postCodeRegex = /^\d{2}-\d{3}$/;
    var phoneRegex = /^(\+48)?\d{9}$/;

    var result = true;

    if(!cityRegex.test(city.value)) {
        result = false;
        city.style.background = "#fac0c0";
    } else {
        city.style.background = null;
    }
    if(!streetRegex.test(street.value)) {
        result = false;
        street.style.background = "#fac0c0";
    } else {
        street.style.background = null;
    }
    if(!noRegex.test(no.value)) {
        result = false;
        no.style.background = "#fac0c0";
    } else {
        no.style.background = null;
    }
    if(!postCodeRegex.test(postCode.value)) {
        result = false;
        postCode.style.background = "#fac0c0";
    } else {
        postCode.style.background = null;
    }
    if(!phoneRegex.test(phoneNumber.value)) {
        result = false;
        phoneNumber.style.background = "#fac0c0";
    } else {
        phoneNumber.style.background = null;
    }
    return result;
}

function validateLoginForm() {
    var login = document.getElementById("login-field");
    var password = document.getElementById("password-field");
    var loginRegex = new RegExp("^\\w{4,}$");
    var passwordRegex = new RegExp("^\\w{5,}$");
    var result = true;
    if(!loginRegex.test(login.value)) {
        result = false;
        login.style.background = "#fac0c0";
    } else {
        login.style.background = null;
    }
    if(!passwordRegex.test(password.value)) {
        result = false;
        password.style.background = "#fac0c0";
    } else {
        password.style.background = null;
    }
    return result;
}

function validateAddBookForm() {
    var title = document.getElementById("title-field");
    var author = document.getElementById("author-field");
    var isbn = document.getElementById("isbn-field");
    var price = document.getElementById("price-field");
    var quantity = document.getElementById("quantity-field");

    var titleRegex = /.+/;
    var authorRegex = /.+/;
    var isbnRegex = /^97[89][\- ]?83([\- ]?\d+){3}[\- ]?\d$/;

    var result = true;

    if(!titleRegex.test(title.value)) {
        result = false;
        title.style.background = "#fac0c0";
    } else {
        title.style.background = null;
    }

    if(!authorRegex.test(author.value)) {
        result = false;
        author.style.background = "#fac0c0";
    } else {
        author.style.background = null;
    }

    if(!isbnRegex.test(isbn.value)) {
        result = false;
        isbn.style.background = "#fac0c0";
    } else {
        isbn.style.background = null;
    }

    if(price.value <= 0 || isNaN(price.value)) {
        result = false;
        price.style.background = "#fac0c0";
    } else {
        price.style.background = null;
    }

    if(quantity.value < 0 || isNaN(quantity.value)) {
        result = false;
        quantity.style.background = "#fac0c0";
    } else {
        quantity.style.background = null;
    }

    return result;
}
