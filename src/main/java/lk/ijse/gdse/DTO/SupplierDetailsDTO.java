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
public class SupplierDetailsDTO {
    private String supplierId;
    private String stockId;
    private Date date;
}
