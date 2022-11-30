package ir.maktab.presentation;

import ir.maktab.data.entity.Payment;
import ir.maktab.data.entity.loans.EducationLoan;
import ir.maktab.data.entity.loans.Loan;
import ir.maktab.data.entity.user.AccountInfo;
import ir.maktab.data.entity.user.Student;
import ir.maktab.data.entity.user.UniversityInfo;
import ir.maktab.data.enums.DegreeType;
import ir.maktab.data.enums.RepayType;
import ir.maktab.data.enums.UniversityType;
import ir.maktab.util.exceptions.NotInDateRangeException;
import ir.maktab.util.exceptions.ValidationException;
import ir.maktab.service.StudentService;
import ir.maktab.util.date.DateUtil;
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
                }
                case 2 -> {
                    signIn();
                }
                case 3 -> {
                }
                default -> {
                }
            }
        } catch (Exception e){
            System.err.println(e.getMessage());
            firstMenu();
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

            System.out.println("Username: ");
            String username = scanner.nextLine();
            Validation.validateUsername(username);

            System.out.println("Password: ");
            String pass = scanner.nextLine();
            Validation.validatePassword(pass);

            AccountInfo account = new AccountInfo(null, username, pass);

            student = new Student(null, name, family, mother, father, bcn, nc, date, false, false
                    , null, null, account, universityInfo);
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
        System.out.println("Username: ");
        String username = scanner.nextLine();
        Validation.validateUsername(username);

        System.out.println("Password: ");
        String pass = scanner.nextLine();
        Validation.validatePassword(pass);

        Optional<Student> signInStudent = studentService.signIn(username, pass);
        student = signInStudent.orElseThrow(() -> new RuntimeException("Invalid Username/Password"));
    }

    public void registerForLoan() {
        //is the date first week of Aban or last week of Bahman
        //show three kinds of loan
        //registerForEducationLoan
        //registerForHouseLoan
        //registerForTuitionLoan
        //call Student service to check prev payment(not in same semester or grade)
        //get card info,validate    or    check isDorm isMarried + get spouse + get contract + get card info
        //call Student service to register payment
    }

    public void repayLoan() {

    }

    public boolean checkRegistrationDate(Date date) {
        if (!DateUtil.isInRange(date))
            throw new NotInDateRangeException("You Can Not Register For A Loan At This Date (Registration Time is First Week " +
                    "Of Aban And Last Week of Bahman)");
        return true;
    }

    public boolean checkRegistrationCondition(Student student) {//todo has some designing issues,fix it when reached
        Loan loan = new EducationLoan(RepayType.EACH_SEMESTER, student.getUniversityInfo().getDegree().toDegreeGroup());
        Payment payment = new Payment(student, loan, student.getUniversityInfo().getDegree());
        return !studentService.hasPreviousLoanPayment(student, payment);

    }
}
