package lk.ijse.gdse.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Data
public class ItemDTO {
    private String itemId;
    private String description;
    private double unitPrice;
    private int QtyOnHand;
    private String stockId;


}
