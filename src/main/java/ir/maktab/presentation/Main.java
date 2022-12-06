package ir.maktab.presentation;

public class Main {
    private static final String DIVIDER = "---------------------------------------------";

    public static void main(String[] args) {
        StudentLoanSystem studentLoanSystem = new StudentLoanSystem();
        System.out.println(DIVIDER);
        System.out.println("Welcome to Student Loan Service");
        System.out.println(DIVIDER);
        studentLoanSystem.firstMenu();
    }

}
