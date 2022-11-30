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
import java.util.Objects;

@DiscriminatorValue(value = "TUITION")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@EqualsAndHashCode
public class TuitionLoan extends Loan {
    @Enumerated(value = EnumType.STRING)
    DegreeGroup degreeGroup;

    public TuitionLoan(Long id, RepayType repayType, double amount, List<Payment> paymentList, DegreeGroup degreeGroup) {
        super(id, repayType, amount, paymentList);
        this.degreeGroup = degreeGroup;
    }

    public TuitionLoan(RepayType repayType, double amount, DegreeGroup degreeGroup) {
        super(repayType, amount);
        this.degreeGroup = degreeGroup;
    }
    public TuitionLoan(RepayType repayType, DegreeGroup degreeGroup) {
        super(repayType);
        this.degreeGroup = degreeGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TuitionLoan)) return false;
        if (!super.equals(o)) return false;
        TuitionLoan that = (TuitionLoan) o;
        return degreeGroup == that.degreeGroup;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), degreeGroup);
    }
}
