package ir.maktab.data.entity.user;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Embeddable
public class AccountInfo implements Serializable {
    @Column(nullable = false, unique = true, updatable = false, length = 10)
    String username;
    @Column(nullable = false, length = 8)//todo test this length
    String password;
}
