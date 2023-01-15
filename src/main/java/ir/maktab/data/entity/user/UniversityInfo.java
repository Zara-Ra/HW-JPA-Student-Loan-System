package ir.maktab.data.entity.user;

import ir.maktab.data.enums.Degree;
import ir.maktab.data.enums.UniversityType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class UniversityInfo {//this entity should be inside Student not an additional entity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false, unique = true)
    String studentNum;
    @Column(nullable = false)
    String universityName;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    UniversityType universityType;
    @Column(nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    Date entryDate;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    Degree degree;
}
