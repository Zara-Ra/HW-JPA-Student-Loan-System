package ir.maktab.data.entity;

import ir.maktab.util.date.DateUtil;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Cacheable                                          //second level cache i dont see any effect!
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQueries(
        @NamedQuery(name = "getAllRepayments",query = "FROM Repayment")
)
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
            return repaymentNum + "- " + DateUtil.dateWithoutTime(dueDate);
        else
            return repaymentNum + "- " + DateUtil.dateWithoutTime(dueDate) + " " + amount;
    }
}
