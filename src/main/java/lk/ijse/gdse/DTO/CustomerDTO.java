package lk.ijse.gdse.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Data
public class CustomerDTO {
    private String cuId;
    private String name;
    private String nicNo;
    private String address;
    private String tel;


}
