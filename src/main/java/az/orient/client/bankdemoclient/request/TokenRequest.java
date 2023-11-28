package az.orient.client.bankdemoclient.request;

import lombok.Data;

@Data
public class TokenRequest {

    private Long userId;
    private String token;

}
