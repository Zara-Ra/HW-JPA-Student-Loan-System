package ir.maktab.data.entity.loans;

import ir.maktab.data.enums.DegreeGroup;
import ir.maktab.data.enums.RepayType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@DiscriminatorValue(value = "TUITION")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TuitionLoan extends Loan {
    @Enumerated(value = EnumType.STRING)
    DegreeGroup degreeGroup;

    public TuitionLoan(RepayType repayType, double amount, DegreeGroup degreeGroup) {
        super(repayType, amount);
        this.degreeGroup = degreeGroup;
    }
}
