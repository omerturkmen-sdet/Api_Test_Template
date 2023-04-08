package pojos.employee;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateEmployeeResponse {

    private String status;
    private Data data;
    private String message;


    public class Data {
        private String name;
        private String salary;
        private String age;
        private String id;
    }

}
