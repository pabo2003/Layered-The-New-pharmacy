package lk.ijse.gdse.DTO;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Data

public class DeliveryDTO {
    private String deliveryId;
    private String description;
    private BigDecimal price;
    private Date deliveryDate;
    private String deliveryStatus;
    private String deliveryMethod;
    private String deliveryAddress;
    private String contactInformation;
    private String trackingNumber;
}
