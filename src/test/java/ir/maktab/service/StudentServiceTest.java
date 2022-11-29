package ir.maktab.service;

import ir.maktab.data.entity.user.AccountInfo;
import ir.maktab.data.entity.user.Student;
import ir.maktab.data.entity.user.UniversityInfo;
import ir.maktab.data.enums.DegreeType;
import ir.maktab.data.enums.UniversityType;
import ir.maktab.util.Dates;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.time.LocalDateTime;
import java.util.Date;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentServiceTest {
    private static final StudentService studentService = StudentService.getInstance();
    @Order(1)
    @Test
    public void signUpTest(){
        AccountInfo accountInfo = new AccountInfo(null,"zara", "12345678");

        UniversityInfo universityInfo = new UniversityInfo(null,"810185193", "TehranUniversity"
                , UniversityType.PUBLIC_DAILY, 2016, DegreeType.BACHELOR);

        LocalDateTime localDate = LocalDateTime.of(1988, 2, 26, 0, 0);
        Date birth = Dates.localDateTimeToDate(localDate);

        Student student = new Student(null, "Zahra", "Rahimi", "Tahere", "Reza"
                , "34123", "0080343453", birth, true,false, null
                , null, accountInfo, universityInfo);

        studentService.singUp(student);
    }
}
