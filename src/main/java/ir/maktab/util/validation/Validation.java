package ir.maktab.util.validation;

import ir.maktab.util.date.DateUtil;
import ir.maktab.util.exceptions.ValidationException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Validation {

    public static TriConsumer validate = (s, r, m) -> {
        if (s.equals("") || !s.matches(r))
            throw new ValidationException(m);
    };

    public static void validateName(String name) {
        validate.accept(name, "^[a-zA-Z ]{2,}", "Invalid Name(Only Alphabetic Characters Accepted)");
    }

    public static void validateBirthCertificate(String code) {
        validate.accept(code, "^([0-9]){3,10}$", "Invalid Birth Certificate Number(3 to 10 Digits Accepted)");
    }

    public static void validateNationalCode(String code) {
        validate.accept(code, "^(?!(\\d)\\1{9})\\d{10}$", "Invalid National Code(10 Digits Accepted)");
    }

    public static void validateStudentNumber(String code) {
        validate.accept(code, "^([0-9]){9}$", "Invalid Student Number(9 Digits Accepted)");
    }

    public static void validateUsername(String username) {
        validate.accept(username, "^(?=[a-zA-Z0-9._]{5,20}$)(?!.*[_.]{2})[^_.].*[^_.]$"
                , "Invalid Username ( 5 to 20 characters)");
    }

    public static void validatePassword(String pass) {
        validate.accept(pass, "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%&]).{8}$",
                "Invalid Password( contain at least one uppercase,one lowercase,one digit,one special character @#$%& )");
    }

    public static void validateCardNumber(String card) {
        validate.accept(card, "603799[0-9]{10}|628023[0-9]{10}|627353[0-9]{10}|589463[0-9]{10}|636214[0-9]{10}"
                , "Invalid Bank ( Melli,Maskan,Tejarat,Refah Accepted )");
        if (!isCardValid(card))
            throw new ValidationException("Invalid Card Number");
    }

    public static boolean isCardValid(String card) {
        char[] chars = card.toCharArray();
        int sum = 0;
        for (int i = 0; i < 16; i++) {
            int numericValue = Character.getNumericValue(chars[i]);
            if (i % 2 == 0) {
                numericValue = numericValue *2;
                if(numericValue > 9)
                    numericValue -= 9;
                sum += numericValue;
            }
            else
                sum += numericValue;
        }
        return sum % 10 == 0;
    }

    public static void validateCvv(String cvv) {

        validate.accept(cvv, "^([0-9]){4}$", "Invalid CVV2(4 digit)");
    }

    public static Date validateExpDate(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dateFormat = formatter.parse(date);
        if(!DateUtil.isDateValid(dateFormat))
            throw new ValidationException("Card Has Expired");
        return dateFormat;
    }

}
