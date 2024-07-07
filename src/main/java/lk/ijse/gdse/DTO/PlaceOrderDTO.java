package lk.ijse.gdse.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlaceOrderDTO {
    private OrderDTO order;
    private List<OrderDetailsDTO> odList;
    private PaymentDTO payment;

}
