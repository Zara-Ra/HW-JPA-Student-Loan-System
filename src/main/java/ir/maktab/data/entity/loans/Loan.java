package ir.maktab.data.entity.loans;

import ir.maktab.data.entity.Payment;
import ir.maktab.data.enums.RepayType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "loan_type", discriminatorType = DiscriminatorType.STRING)
@Entity
public class Loan implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Exclude
    Long id;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    RepayType repayType;
    double amount;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "loan")
    List<Payment> paymentList = new ArrayList<>();

    public Loan(RepayType repayType) {
        this.repayType = repayType;
    }

    public Loan(RepayType repayType, double amount) {
        this.repayType = repayType;
        this.amount = amount;
    }
}
