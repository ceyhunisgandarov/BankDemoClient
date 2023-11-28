package az.orient.client.bankdemoclient.response;

import lombok.Data;

@Data
public class UserResponse {

    private String username;
    private String fullName;
    private TokenResponse tokenResponse;
}
