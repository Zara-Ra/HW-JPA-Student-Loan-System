package ir.maktab.data.entity;

import ir.maktab.data.entity.loans.Loan;
import ir.maktab.data.entity.user.Student;
import ir.maktab.data.enums.DegreeType;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
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

    @EqualsAndHashCode.Exclude
    @Temporal(value = TemporalType.TIMESTAMP)
    @CreationTimestamp
    Date paidDate;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    DegreeType degreeType;
    @EqualsAndHashCode.Exclude
    @OneToOne(cascade = CascadeType.MERGE)
    CreditCard creditCard;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "payment", cascade = CascadeType.MERGE)// fetch ??
    List<Repayment> repaymentList = new ArrayList<>();

    public Payment(Student student, Loan loan, DegreeType degreeType) {
        this.student = student;
        this.loan = loan;
        this.degreeType = degreeType;
    }
}
