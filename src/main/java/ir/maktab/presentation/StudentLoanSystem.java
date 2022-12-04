package ir.maktab.presentation;

import ir.maktab.data.entity.CreditCard;
import ir.maktab.data.entity.Payment;
import ir.maktab.data.entity.loans.Loan;
import ir.maktab.data.entity.user.AccountInfo;
import ir.maktab.data.entity.user.Person;
import ir.maktab.data.entity.user.Student;
import ir.maktab.data.entity.user.UniversityInfo;
import ir.maktab.data.enums.City;
import ir.maktab.data.enums.DegreeType;
import ir.maktab.data.enums.UniversityType;
import ir.maktab.service.LoanService;
import ir.maktab.service.PaymentService;
import ir.maktab.service.StudentService;
import ir.maktab.util.date.DateUtil;
import ir.maktab.util.exceptions.NotInDateRangeException;
import ir.maktab.util.exceptions.ValidationException;
import ir.maktab.util.validation.Validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;

import static java.lang.System.exit;

public class StudentLoanSystem {
    private final Scanner scanner = new Scanner(System.in);
    private static final String DIVIDER = "---------------------------------------------";

    public static final StudentService studentService = StudentService.getInstance();
    public static final PaymentService paymentService = PaymentService.getInstance();
    public static final LoanService loanService = LoanService.getInstance();
    private Student student;

    public void firstMenu() {
        System.out.println("Press 1 --> Sign Up");
        System.out.println("Press 2 --> Sign In");
        System.out.println("Press 3  --> Sign Out");
        System.out.println("Press any Key to Exit");
        System.out.println(DIVIDER);
        int choice = 0;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            exit(0);
        }
        try {
            switch (choice) {
                case 1 -> {
                    signUp();
                    secondMenu();
                }
                case 2 -> {
                    signIn();
                    secondMenu();
                }
                case 3 -> {
                }
                default -> {
                    exit(0);
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            firstMenu();
        }
    }

    private void secondMenu() {
        System.out.println("Press 1 --> Register For Loan");
        System.out.println("Press 2 --> Repay Loan");
        System.out.println("Press any Key to Exit");
        System.out.println(DIVIDER);
        int choice = 0;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            exit(0);
        }
        try {
            switch (choice) {
                case 1 -> {
                    registerForLoan();
                    secondMenu();
                }
                case 2 -> {
                    repayLoan();
                    secondMenu();
                }
                default -> {
                    exit(0);
                }
            }
        } catch (NotInDateRangeException e) {
            System.err.println(e.getMessage());
            secondMenu();
        }
    }

    private void signUp() {
        try {
            System.out.println("Name: ");
            String name = scanner.nextLine();
            Validation.validateName(name);

            System.out.println("Family name: ");
            String family = scanner.nextLine();
            Validation.validateName(family);

            System.out.println("Mothers name: ");
            String mother = scanner.nextLine();
            Validation.validateName(mother);

            System.out.println("Fathers name: ");
            String father = scanner.nextLine();
            Validation.validateName(father);

            System.out.println("Birth Certificate Number: ");
            String bcn = scanner.nextLine();
            Validation.validateBirthCertificate(bcn);

            System.out.println("National Code: ");
            String nc = scanner.nextLine();
            Validation.validateNationalCode(nc);

            System.out.println("Birthdate:(exp. 2001-01-23) ");
            String birthdate = scanner.nextLine();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = formatter.parse(birthdate);

            System.out.println("City:");
            String cityName = scanner.nextLine();
            City city = City.valueOfCity(cityName);

            System.out.println("University name: ");
            String uniName = scanner.nextLine();
            Validation.validateName(uniName);

            System.out.println("University Type: ");
            String uniType = scanner.nextLine();
            UniversityType universityType = UniversityType.valueOf(uniType);

            System.out.println("Student Number: ");
            String studentNum = scanner.nextLine();
            Validation.validateStudentNumber(studentNum);

            System.out.println("Entry Year: ");
            String entryYear = scanner.nextLine();
            int year = Integer.parseInt(entryYear);

            System.out.println("Degree Type: ");
            String degreeType = scanner.nextLine();
            DegreeType degreeType1 = DegreeType.valueOf(degreeType);

            UniversityInfo universityInfo = new UniversityInfo(null, studentNum, uniName, universityType
                    , year, degreeType1);

            System.out.println("Password: ");
            String pass = scanner.nextLine();
            Validation.validatePassword(pass);

            AccountInfo account = new AccountInfo(null, nc, pass);

            student = new Student(null, name, family, mother, father, bcn, nc, date, false, false
                    , city, null, account, universityInfo);
            studentService.singUp(student);

        } catch (ParseException e) {
            throw new RuntimeException("Invalid Date Format");
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid Year");
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid University/Degree Type");
        } catch (ValidationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private void signIn() {
        System.out.println("Username:(National Number) ");
        String username = scanner.nextLine();
        Validation.validateNationalCode(username);

        System.out.println("Password: ");
        String pass = scanner.nextLine();
        Validation.validatePassword(pass);

        Optional<Student> signInStudent = studentService.signIn(username, pass);
        student = signInStudent.orElseThrow(() -> new RuntimeException("Invalid Username/Password"));
    }

    public void registerForLoan() {
        //paymentService.checkRegistrationDate(TODAY_DATE);
        paymentService.checkRegistrationDate(DateUtil.getToday());
        studentService.isGraduated(student);
        System.out.println("Press 1 --> Education Loan");
        System.out.println("Press 2 --> Tuition Loan");
        System.out.println("Press 3 --> Housing Loan ");
        System.out.println("Press any key --> Back");
        System.out.println(DIVIDER);
        int choice = 0;
        Loan loan;
        Payment payment = null;
        boolean hasPayment = true;
        boolean hasConditions = false;
        try {
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> {
                    hasConditions = true;
                    loan = loanService.getEducationLoan(student.getUniversityInfo().getDegree().toDegreeGroup());
                    payment = new Payment(student, loan, student.getUniversityInfo().getDegree());
                    hasPayment = studentService.hasPreviousLoanPayment(student, payment);
                }
                case 2 -> {
                    hasConditions = checkTuitionLoanConditions();
                    if (hasConditions) {
                        loan = loanService.getTuitionLoan(student.getUniversityInfo().getDegree().toDegreeGroup());
                        payment = new Payment(student, loan, student.getUniversityInfo().getDegree());
                        hasPayment = studentService.hasPreviousLoanPayment(student, payment);
                    }
                }
                case 3 -> {
                    loan = loanService.getHousingLoan(student.getCity().type);
                    payment = new Payment(student, loan, student.getUniversityInfo().getDegree());
                    hasPayment = studentService.hasPreviousLoanPayment(student, payment);
                    if (!hasPayment)
                        hasConditions = checkHousingLoanConditions();
                }
                default -> throw new NumberFormatException();
            }

            if (!hasPayment && hasConditions) {
                CreditCard creditCard = getCreditCardInfo();
                payment.setCreditCard(creditCard);
                paymentService.registerPayment(payment);
            } else {
                System.err.println("You Don't Have The Conditions To Register For This Loan");
            }
        } catch (NumberFormatException e) {
            secondMenu();
        } catch (ValidationException | ParseException e) {
            System.err.println(e.getMessage());
            secondMenu();
        }
    }

    private boolean checkHousingLoanConditions() {
        System.out.println("Are You Married?(Y/N)");
        String yesNo = scanner.nextLine();
        if (yesNo.equals("Y") || yesNo.equals("y")) {
            student.setMarried(true);
        } else
            return false;
        System.out.println("Do You Live in University Dorm?(Y/N)");
        yesNo = scanner.nextLine();
        if (yesNo.equals("N") || yesNo.equals("n")) {
            student.setLiveInDorm(false);
        } else
            return false;
        System.out.println("Enter Your Spouse Information");        //todo what should be compared for spouse
        System.out.println("Name:");                                //maybe write a query to find person in payment
        String spouseName = scanner.nextLine();
        Validation.validateName(spouseName);
        System.out.println("Family Name:");                         //with national code
        String spouseFamily = scanner.nextLine();
        Validation.validateName(spouseFamily);
        System.out.println("National Code:");
        String spouseNc = scanner.nextLine();
        Validation.validateNationalCode(spouseNc);
        System.out.println("Enter House Contract Number:");
        student.setHouseContractNum(scanner.nextLine());
        Person spouse = new Person(spouseName, spouseFamily, spouseNc);
        return paymentService.checkSpouseConditions(student, spouse);
    }

    private boolean checkTuitionLoanConditions() {
        return !(student.getUniversityInfo().getUniversityType() == UniversityType.PUBLIC_DAILY);
    }

    private CreditCard getCreditCardInfo() throws ParseException {
        System.out.println("Enter Your 16 Digit Card Number(Melli,Maskan,Tejarat,Refah):");
        String card = scanner.nextLine();
        Validation.validateCardNumber(card);

        System.out.println("Enter cvv2:");
        String cvv2 = scanner.nextLine();
        Validation.validateCvv(cvv2);

        System.out.println("Enter Expiry Date:");
        String expDate = scanner.nextLine();
        Date date = Validation.validateExpDate(expDate);

        return new CreditCard(null, card, cvv2, date);
    }


    public void repayLoan() {

    }

}
