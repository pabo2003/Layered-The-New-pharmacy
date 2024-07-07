package lk.ijse.gdse.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Data
public class StockDTO {
    private String stockId;
    private String description;
    private String category;
}
