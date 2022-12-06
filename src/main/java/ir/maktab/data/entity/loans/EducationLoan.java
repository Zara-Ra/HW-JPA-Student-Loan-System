package ir.maktab.data.entity.loans;

import ir.maktab.data.enums.DegreeGroup;
import ir.maktab.data.enums.RepayType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@DiscriminatorValue(value = "EDUCATION")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class EducationLoan extends Loan {
    @Enumerated(value = EnumType.STRING)
    DegreeGroup degreeGroup;

    public EducationLoan(RepayType repayType, double amount, DegreeGroup degreeGroup) {
        super(repayType, amount);
        this.degreeGroup = degreeGroup;
    }
}
