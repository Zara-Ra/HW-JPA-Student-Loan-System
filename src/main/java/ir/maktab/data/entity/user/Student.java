package ir.maktab.data.entity.user;

import ir.maktab.data.entity.Payment;
import ir.maktab.data.enums.City;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Student extends Person implements Serializable {
    @OneToOne(cascade = CascadeType.ALL)
    AccountInfo accountInfo;
    @OneToOne(cascade = CascadeType.ALL)
    UniversityInfo universityInfo;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
    List<Payment> paymentList = new ArrayList<>();

    public Student(Long id, String name, String familyName, String mothersName, String fathersName, String birthCertificateNum, String nationalNum, Date birthdate, boolean isMarried, boolean liveInDorm, City city, String houseContractNum, AccountInfo accountInfo, UniversityInfo universityInfo) {
        super(id, name, familyName, mothersName, fathersName, birthCertificateNum, nationalNum, birthdate, isMarried, liveInDorm, city, houseContractNum);
        this.accountInfo = accountInfo;
        this.universityInfo = universityInfo;
    }

    @Override
    public String toString() {
        return "Student{" +
                "Name=" + super.name + " " + super.familyName +
                ", Student number=" + universityInfo.getStudentNum() +
                '}';
    }
}
