package lk.ijse.gdse.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Data
public class EmployeeDTO {
    private String employeeId;
    private String name;
    private String NICNo;
    private String address;
    private String tel;
    private double salary;
}

