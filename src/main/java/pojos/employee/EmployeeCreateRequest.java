package pojos.employee;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeCreateRequest {

    private String name;
    private String salary;
    private String age;

}
