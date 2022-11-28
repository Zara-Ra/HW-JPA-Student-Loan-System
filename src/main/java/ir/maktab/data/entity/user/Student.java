package ir.maktab.data.entity.user;

import ir.maktab.data.entity.Payment;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Student extends Person implements Serializable {
    @Embedded
    AccountInfo accountInfo;
    @Embedded
    StudentUniversityInfo universityInfo;

    @OneToMany(mappedBy = "student")
    List<Payment> paymentList = new ArrayList<>();

    public Student(Long id, String name, String familyName, String mothersName, String fathersName, String birthCertificateNum, String nationalNum, Date birthdate, boolean liveInDorm, String address, String houseContractNum, AccountInfo accountInfo, StudentUniversityInfo universityInfo) {
        super(id, name, familyName, mothersName, fathersName, birthCertificateNum, nationalNum, birthdate, liveInDorm, address, houseContractNum);
        this.accountInfo = accountInfo;
        this.universityInfo = universityInfo;
    }
}
