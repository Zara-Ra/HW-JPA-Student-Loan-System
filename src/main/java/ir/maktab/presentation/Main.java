package ir.maktab.presentation;

public class Main {
    private static final String DIVIDER = "---------------------------------------------";
    public static void main(String[] args) {
        StudentLoanSystem studentLoanSystem = new StudentLoanSystem();
        System.out.println(DIVIDER);
        System.out.println("Welcome to Student Loan Service");
        System.out.println(DIVIDER);
        studentLoanSystem.firstMenu();
        /*LoanService loanService = LoanService.getInstance();
        StudentService studentService = StudentService.getInstance();
        PaymentService paymentService = PaymentService.getInstance();

        AccountInfo accountInfo = new AccountInfo(null,"zara", "12345678");
        UniversityInfo universityInfo = new UniversityInfo(null,"810185193", "Tehran"
                , UniversityType.PUBLIC_DAILY, 2016, DegreeType.BACHELOR);

        LocalDateTime localDate = LocalDateTime.of(2022, 10, 24, 0, 0);
        Date birth = Dates.localDateTimeToDate(localDate);
        Student student = new Student(null, "Zahra", "Rahimi", "Tahere", "Reza"
                , "1222", "088021", birth, true,false, null
                , null, accountInfo, universityInfo);
        studentService.singUp(student);

        Loan loan = new EducationLoan(null, RepayType.EACH_SEMESTER, 12000d, null, DegreeGroup.GROUP1);
        loanService.addLoan(loan);

        Optional<Student> sara = StudentService.getInstance().signIn("zara", "12345678");
        sara.ifPresent(System.out::println);

        Payment payment = new Payment(student, loan, student.getUniversityInfo().getDegree());
        paymentService.registerPayment(payment);*/

    }

}
