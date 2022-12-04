package ir.maktab.data.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Repayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    Payment payment;
    int repaymentNum;
    double amount;
    @Temporal(value = TemporalType.TIMESTAMP)
    Date dueDate;
    boolean isPaid;

    @Override
    public String toString() {
        if (isPaid)
            return repaymentNum + "- " + dueDate;
        else
            return repaymentNum + "- " + dueDate + " " + amount;
    }
}
