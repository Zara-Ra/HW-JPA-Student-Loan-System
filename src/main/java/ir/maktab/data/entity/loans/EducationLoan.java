package ir.maktab.data.entity.loans;

import ir.maktab.data.entity.Payment;
import ir.maktab.data.enums.DegreeGroup;
import ir.maktab.data.enums.RepayType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

@DiscriminatorValue(value = "EDUCATION")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class EducationLoan extends Loan {
    @Enumerated(value = EnumType.STRING)
    DegreeGroup degreeGroup;

    public EducationLoan(Long id, RepayType repayType, double amount, List<Payment> paymentList, DegreeGroup degreeGroup) {
        super(id, repayType, amount, paymentList);
        this.degreeGroup = degreeGroup;
    }
}
