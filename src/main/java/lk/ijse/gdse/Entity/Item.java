package lk.ijse.gdse.Entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Data
public class Item {
    private String itemId;
    private String description;
    private double unitPrice;
    private int QtyOnHand;
    private String stockId;


}
