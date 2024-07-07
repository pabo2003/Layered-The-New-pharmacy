package lk.ijse.gdse.DTO;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Data
public class PaymentDTO {
    private String payId;
    private String method;
    private double amount;
    private Date date;
}

