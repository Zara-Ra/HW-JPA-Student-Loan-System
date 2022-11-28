package ir.maktab.data.entity.user;

import ir.maktab.data.enums.DegreeType;
import ir.maktab.data.enums.PublicUniversityType;
import ir.maktab.data.enums.UniversityType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Embeddable
public class StudentUniversityInfo {
    @Column(nullable = false)
    String studentNum;
    @Column(nullable = false)
    String universityName;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    UniversityType universityType;
    @Enumerated(value = EnumType.STRING)
    PublicUniversityType publicUniversityType;
    @Column(nullable = false)
    int enteringYear;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    DegreeType degree;
}
