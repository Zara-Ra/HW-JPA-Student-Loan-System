package ir.maktab.data.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@ToString isPaid=false we need number and amount and date,isPaid=true we need number and date
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Repayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    Payment payment;
    int repaymentNum;//1-60
    double amount;
    @Temporal(value = TemporalType.DATE)
    Date dueDate;
    boolean isPaid = false;

}
