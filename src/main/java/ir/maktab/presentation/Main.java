package ir.maktab.presentation;

import ir.maktab.data.entity.loans.EducationLoan;
import ir.maktab.data.entity.loans.Loan;
import ir.maktab.data.entity.user.AccountInfo;
import ir.maktab.data.entity.user.Student;
import ir.maktab.data.entity.user.StudentUniversityInfo;
import ir.maktab.data.enums.*;
import ir.maktab.repository.LoanRepo;
import ir.maktab.repository.StudentRepo;
import ir.maktab.service.StudentService;
import ir.maktab.util.Dates;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        //new StudentLoanSystem().FirstMenu();

        StudentRepo studentRepo = StudentRepo.getInstance();
        LoanRepo loanRepo = LoanRepo.getInstance();

        AccountInfo accountInfo = new AccountInfo("zara", "12345678");
        StudentUniversityInfo universityInfo = new StudentUniversityInfo("810185193", "Tehran"
                , UniversityType.PUBLIC_DAILY, 2016, DegreeType.BACHELOR);

        LocalDateTime localDate = LocalDateTime.of(2022, 10, 24, 0, 0);
        Date birth = Dates.localDateTimeToDate(localDate);

        Student student = new Student(null, "Zahra", "Rahimi", "Tahere", "Reza"
                , "1222", "088021", birth, true,false, null
                , null, accountInfo, universityInfo);
        studentRepo.save(student);

        Loan loan = new EducationLoan(null, RepayType.EACH_SEMESTER, 12000d, null, DegreeGroup.GROUP1);
        loanRepo.save(loan);

        Optional<Student> sara = StudentService.getInstance().signIn("sara", "12345678");
        sara.ifPresent(System.out::println);

    }

}
