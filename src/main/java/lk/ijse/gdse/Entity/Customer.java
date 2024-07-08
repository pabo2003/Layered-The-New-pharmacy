package lk.ijse.gdse.Entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Data
public class Customer {
    private String cuId;
    private String name;
    private String nicNo;
    private String address;
    private String tel;


}
