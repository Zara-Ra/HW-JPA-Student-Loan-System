package ir.maktab.data.entity;

import ir.maktab.data.entity.loans.Loan;
import ir.maktab.data.entity.user.Student;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentId implements Serializable {
    Student student;
    Loan loan;
}
