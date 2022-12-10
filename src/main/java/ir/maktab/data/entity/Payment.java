package ir.maktab.data.entity;

import ir.maktab.data.entity.loans.Loan;
import ir.maktab.data.entity.user.Student;
import ir.maktab.data.enums.Degree;
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
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@IdClass(PaymentId.class)
@NamedQueries(
        @NamedQuery(name = "getAllPayments",query = "FROM Payment")
)
public class Payment {
    @Id
    @ManyToOne
    Student student;
    @Id
    @ManyToOne
    Loan loan;

    @EqualsAndHashCode.Exclude
    @Temporal(value = TemporalType.TIMESTAMP)
    //@CreationTimestamp --> this filed should actually be timestamp but for testing issues it is set manually
    Date paidDate;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    Degree degree;
    @EqualsAndHashCode.Exclude
    @OneToOne(cascade = CascadeType.MERGE)
    CreditCard creditCard;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "payment", cascade = CascadeType.MERGE)
    List<Repayment> repaymentList = new ArrayList<>();

    public Payment(Student student, Loan loan, Degree degree) {
        this.student = student;
        this.loan = loan;
        this.degree = degree;
    }

    @Override
    public String toString() {
        return "***Payment: "
                + student.getName() + " "
                + student.getFamilyName() + " "
                + loan.getClass().getSimpleName() + "***";
    }
}
