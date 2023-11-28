package az.orient.client.bankdemoclient.request;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ReqCustomer {

    private String name;
    private String surname;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;
    private String address;
    private String phone;
    private String pin;
    private String serialNumber;
    private String cif;
    private TokenRequest tokenRequest;

}
