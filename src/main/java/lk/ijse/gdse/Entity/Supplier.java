package lk.ijse.gdse.Entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Data
public class Supplier {
    private String supplierId;
    private String name;
    private String description;
    private String address;
    private String tel;


}

