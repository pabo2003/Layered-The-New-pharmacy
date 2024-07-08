package lk.ijse.gdse.Entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Data
public class Stock {
    private String stockId;
    private String description;
    private String category;
}
