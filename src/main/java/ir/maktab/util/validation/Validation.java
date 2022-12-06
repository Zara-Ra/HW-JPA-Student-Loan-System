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
        if (!isNationalCodeValid(code))
            throw new ValidationException("Invalid National Code");
    }

    private static boolean isNationalCodeValid(String code) {
        int sum = 0;
        char[] chars = code.toCharArray();
        for (int i = 10; i >= 2; i--) {
            int temp = Character.getNumericValue(chars[10 - i]) * i;
            sum += temp;
        }
        int controlDigit = Character.getNumericValue(chars[9]);
        int mod = sum % 11;
        if (mod < 2)
            return controlDigit == mod;
        else
            return controlDigit == 11 - mod;
    }

    public static void validateStudentNumber(String code) {
        validate.accept(code, "^([0-9]){9}$", "Invalid Student Number(9 Digits Accepted)");
    }

    public static void validatePassword(String pass) {
        validate.accept(pass, "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%&]).{8}$",
                "Invalid Password( 8 characters,contain at least one uppercase,one lowercase,one digit,one special character @#$%& )");
    }

    public static void validateCardNumber(String card) {
        validate.accept(card, "603799[0-9]{10}|628023[0-9]{10}|627353[0-9]{10}|589463[0-9]{10}|636214[0-9]{10}"
                , "Invalid Bank ( Melli,Maskan,Tejarat,Refah Accepted )");
        if (!isCardValid(card))
            throw new ValidationException("Invalid Card Number");
    }

    private static boolean isCardValid(String card) {
        char[] chars = card.toCharArray();
        int sum = 0;
        for (int i = 0; i < 16; i++) {
            int numericValue = Character.getNumericValue(chars[i]);
            if (i % 2 == 0) {
                numericValue = numericValue * 2;
                if (numericValue > 9)
                    numericValue -= 9;
                    sum += numericValue;
            } else
                sum += numericValue;
        }
        return sum % 10 == 0;
    }

    public static void validateCvv(String cvv) {

        validate.accept(cvv, "^([0-9]){4}$", "Invalid cvv2 (4 Digits Accepted)");
    }

    public static Date validateExpDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dateFormat;
        try {
            dateFormat = formatter.parse(date);
        } catch (ParseException e) {
            throw new ValidationException("Invalid Date Format");
        }
        if (!DateUtil.isDateValid(dateFormat))
            throw new ValidationException("Card Has Expired");
        return dateFormat;
    }

}
