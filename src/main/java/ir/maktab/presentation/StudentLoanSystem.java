package ir.maktab.presentation;

import ir.maktab.data.entity.Payment;
import ir.maktab.data.entity.loans.EducationLoan;
import ir.maktab.data.entity.loans.Loan;
import ir.maktab.data.entity.user.Student;
import ir.maktab.data.enums.RepayType;
import ir.maktab.exceptions.NotInDateRangeException;
import ir.maktab.service.StudentService;
import ir.maktab.util.Dates;

import java.util.Date;

public class StudentLoanSystem {
        public static final StudentService studentService = StudentService.getInstance();
        public void firstMenu(){
                //signIn
                //signUp
        }
        public void registerForLoan(){
                //is the date first week of Aban or last week of Bahman
                //show three kinds of loan
                //registerForEducationLoan
                //registerForHouseLoan
                //registerForTuitionLoan
                //call Student service to check prev payment(not in same semester or grade)
                //get card info,validate    or    check isDorm isMarried + get spouse + get contract + get card info
                //call Student service to register payment
        }
        public void repayLoan(){

        }
        public boolean checkRegistrationDate(Date date){
                if(!Dates.isInRange(date))
                        throw new NotInDateRangeException("You Can Not Register For A Loan At This Date (Registration Time is First Week " +
                                "Of Aban And Last Week of Bahman)");
                return true;
        }
        public boolean checkRegistrationCondition(Student student){//todo has some designing issues,fix it when reached
                Loan loan = new EducationLoan(RepayType.EACH_SEMESTER, student.getUniversityInfo().getDegree().toDegreeGroup());
                Payment payment = new Payment(student, loan, student.getUniversityInfo().getDegree());
                return !studentService.hasPreviousLoanPayment(student,payment);

        }
}
