package lk.ijse.gdse.Entity;

import lk.ijse.gdse.DTO.PlaceOrderDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlaceOrder {
    private Order order;
    private List<OrderDetails> odList;
    private Payment payment;

    public PlaceOrder(PlaceOrderDTO po1) {
    }

}
