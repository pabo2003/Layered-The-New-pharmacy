package lk.ijse.gdse.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Data
public class OrderDetailsDTO {
    private String itemId;
    private String orderId;
    private int qty;
    private double unitPrice;
}
