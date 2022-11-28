package ir.maktab.data.entity;

import ir.maktab.data.entity.loans.Loan;
import ir.maktab.data.entity.user.Student;
import ir.maktab.data.enums.DegreeType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@IdClass(PaymentId.class)
public class Payment {
    @Id
    @ManyToOne
    Student student;
    @Id
    @ManyToOne
    Loan loan;

    @Temporal(value = TemporalType.DATE)
    Date paidDate;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    DegreeType degreeType;
    @OneToOne
    CreditCard creditCard;

    @OneToMany(mappedBy = "payment")
    List<Repayment> repaymentList = new ArrayList<>();

    @Override
    public String toString() {
        return "Loan Payment{" +
                "Username:" + student.getName() + " " + student.getFamilyName() +
                ", Loan:" + loan.toString() +
                ", paidDate:" + paidDate +
                ", degreeType:" + degreeType +
                ", creditCard:" + creditCard +
                '}';
    }

}
