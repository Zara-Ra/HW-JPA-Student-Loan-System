package ir.maktab.data.entity.user;

import ir.maktab.data.enums.City;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public class Person {   //personal info
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    String familyName;
    @Column(nullable = false)
    String mothersName;
    @Column(nullable = false)
    String fathersName;
    @Column(nullable = false, unique = true)
    String birthCertificateNum;
    @Column(nullable = false, unique = true)
    String nationalNum;
    @Column(nullable = false)
    @Temporal(value = TemporalType.DATE)
    Date birthdate;

    @EqualsAndHashCode.Exclude
    boolean isMarried;
    @EqualsAndHashCode.Exclude
    boolean liveInDorm;
    @EqualsAndHashCode.Exclude
    @Enumerated(value = EnumType.STRING)
    City city;
    @EqualsAndHashCode.Exclude
    String houseContractNum;

    public Person(String name, String familyName, String nationalNum) {
        this.name = name;
        this.familyName = familyName;
        this.nationalNum = nationalNum;
    }
}

