package ir.maktab.data.entity.loans;

import ir.maktab.data.entity.Payment;
import ir.maktab.data.enums.CityType;
import ir.maktab.data.enums.RepayType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

@DiscriminatorValue(value = "HOUSE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
//equalhash
public class HousingLoan extends Loan {
    @Enumerated(value = EnumType.STRING)
    CityType cityType;

    public HousingLoan(Long id, RepayType repayType, double amount, List<Payment> paymentList, CityType cityType) {
        super(id, repayType, amount, paymentList);
        this.cityType = cityType;
    }
}
