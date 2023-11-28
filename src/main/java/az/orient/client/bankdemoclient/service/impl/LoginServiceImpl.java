package az.orient.client.bankdemoclient.service.impl;

import az.orient.client.bankdemoclient.request.UserRequest;
import az.orient.client.bankdemoclient.response.Response;
import az.orient.client.bankdemoclient.response.TokenResponse;
import az.orient.client.bankdemoclient.response.UserResponse;
import az.orient.client.bankdemoclient.service.LoginService;
import az.orient.client.bankdemoclient.utility.Utility;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@PropertySource("classpath:config.properties")
public class LoginServiceImpl implements LoginService {

    private final Utility utility;

    private final ObjectMapper objectMapper;

    @Value("${api.url}")
    private String apiUrl;

    @Override
    public Response<UserResponse> login(UserRequest userRequest) throws Exception {
        String reqLoginJson = objectMapper.writeValueAsString(userRequest);
        String result = utility.sendPost(apiUrl + "user/auth", reqLoginJson);
        Response<UserResponse> userResponse = objectMapper.readValue(result, new TypeReference<>() {});
        return userResponse;
    }

    @Override
    public Response logout(TokenResponse tokenResponse) throws Exception {
        String tokenResponseJson = objectMapper.writeValueAsString(tokenResponse);
        String result = utility.sendPost(apiUrl + "user/logout", tokenResponseJson);
        Response response = objectMapper.readValue(result, new TypeReference<Response>() {});
        return response;
    }
}
