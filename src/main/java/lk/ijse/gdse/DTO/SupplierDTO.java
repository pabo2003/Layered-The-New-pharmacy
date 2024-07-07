package lk.ijse.gdse.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Data
public class SupplierDTO {
    private String supplierId;
    private String name;
    private String description;
    private String address;
    private String tel;


}

