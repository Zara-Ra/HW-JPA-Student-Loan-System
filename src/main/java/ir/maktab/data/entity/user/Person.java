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
public class Person {
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
    @Column(nullable = false)
    String birthCertificateNum;
    @Column(nullable = false, unique = true)
    String nationalCode;
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
    //Person spouse;
    public Person(String name, String familyName, String nationalCode) {
        this.name = name;
        this.familyName = familyName;
        this.nationalCode = nationalCode;
    }
}

