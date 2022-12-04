package ir.maktab.service;

import ir.maktab.data.entity.user.AccountInfo;
import ir.maktab.data.entity.user.Student;
import ir.maktab.data.entity.user.UniversityInfo;
import ir.maktab.data.enums.City;
import ir.maktab.data.enums.DegreeType;
import ir.maktab.data.enums.UniversityType;
import ir.maktab.util.date.DateUtil;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentServiceTest {
    private static final StudentService studentService = StudentService.getInstance();
    private Student student;
    @Order(1)
    @Test
    public void signUpTest(){
        AccountInfo accountInfo = new AccountInfo(null,"0080218725", "aA1@zzzz");

        UniversityInfo universityInfo = new UniversityInfo(null,"810185193", "Tehran University"
                , UniversityType.PRIVATE, 2021, DegreeType.BACHELOR);

        LocalDateTime localDate = LocalDateTime.of(1988, 2, 26, 0, 0);
        Date birth = DateUtil.localDateTimeToDate(localDate);

        student = new Student(null, "Zahra", "Rahimi", "Tahere", "Reza"
                , "34123", "0080218725", birth, false,false, City.TEHRAN
                , null, accountInfo, universityInfo);

        studentService.singUp(student);
    }
    @Order(2)
    @Test
    public void signInTest(){
        Optional<Student> optionalStudent = studentService.signIn("0080218725", "aA1@zzzz");
        assertTrue(optionalStudent.isPresent());
    }

    @Order(2)
    @Test
    public void invalidUsernameSignInTest(){
        Optional<Student> optionalStudent = studentService.signIn("12345678","aA1@zzzz");
        assertFalse(optionalStudent.isPresent());
    }

    @Order(3)
    @Test
    public void invalidPasswordSignInTest(){
        Optional<Student> optionalStudent = studentService.signIn("0080218725","aaaaaaaa");
        assertFalse(optionalStudent.isPresent());
    }
}
