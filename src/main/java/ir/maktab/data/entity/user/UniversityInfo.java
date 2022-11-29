package ir.maktab.data.entity.user;

import ir.maktab.data.enums.DegreeType;
import ir.maktab.data.enums.UniversityType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class UniversityInfo {
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
    int enteringYear;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    DegreeType degree;
}
