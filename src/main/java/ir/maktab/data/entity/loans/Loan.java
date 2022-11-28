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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "loan_type", discriminatorType = DiscriminatorType.STRING)
@Entity
public class Loan implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    /*@Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    LoanType loanType;
    */
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    RepayType repayType;
    double amount;

    @ToString.Exclude
    @OneToMany(mappedBy = "loan")
    List<Payment> paymentList = new ArrayList<>();
}
