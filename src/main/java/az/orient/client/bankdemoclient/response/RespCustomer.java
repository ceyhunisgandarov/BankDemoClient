package az.orient.client.bankdemoclient.response;

import lombok.Data;

import java.util.Date;

@Data
public class RespCustomer {

    private Long id;
    private String name;
    private String surname;
    private Date dob;
    private String address;
    private String phone;
    private String pin;
    private String serialNumber;
    private String cif;
    private Date createdAt;

}
