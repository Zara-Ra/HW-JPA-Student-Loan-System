package ir.maktab.data.entity.loans;

import ir.maktab.data.enums.CityType;
import ir.maktab.data.enums.RepayType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@DiscriminatorValue(value = "HOUSE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class HousingLoan extends Loan {
    @Enumerated(value = EnumType.STRING)
    CityType cityType;

    public HousingLoan(RepayType repayType, double amount, CityType cityType) {
        super(repayType, amount);
        this.cityType = cityType;
    }
}
